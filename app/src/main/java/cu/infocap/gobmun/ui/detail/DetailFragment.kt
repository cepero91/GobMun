package cu.infocap.gobmun.ui.detail

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.coroutinespermission.PermissionManager
import com.squareup.picasso.Picasso
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.FragmentDetailBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.util.Constants
import cu.infocap.gobmun.util.setCompatibilityHtmlText
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random


class DetailFragment : DaggerFragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailBinding
    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + parentJob)

    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(Constants.EXTRA_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        data?.let { item ->
            binding.btnSearch.setOnClickListener(this)
            binding.tvTitle.text = item.name
            binding.tvDetail.setCompatibilityHtmlText(item.description)
            val androidColors = resources.getIntArray(R.array.androidcolors)
            val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
            binding.ivDetail.setBackgroundColor(randomAndroidColor)
            if (item.image.isEmpty().not()) {
                Picasso.get().load(item.image).into(binding.ivDetail)
            }
            binding.contactLayout.contactContainer.visibility = View.VISIBLE
            binding.contactLayout.rlPrimaryPhone.visibility = View.GONE
            binding.contactLayout.rlSecundaryPhone.visibility = View.GONE
            binding.contactLayout.rlEmail.visibility = View.GONE
            binding.contactLayout.rlAddress.visibility = View.GONE
            if (item.phone.isNullOrEmpty().not()) {
                binding.contactLayout.rlPrimaryPhone.visibility = View.VISIBLE
                binding.contactLayout.tvPrimaryPhone.text = item.phone
                binding.contactLayout.rlPrimaryPhone.setOnClickListener {
                    checkAndCallPhone(item.phone!!)
                }
            }
            if (item.secondaryPhone.isNullOrEmpty().not()) {
                binding.contactLayout.rlSecundaryPhone.visibility = View.VISIBLE
                binding.contactLayout.tvSecundaryPhone.text = item.secondaryPhone
                binding.contactLayout.rlSecundaryPhone.setOnClickListener {
                    checkAndCallPhone(item.secondaryPhone!!)
                }
            }
            if (item.email.isNullOrEmpty().not()) {
                binding.contactLayout.rlEmail.visibility = View.VISIBLE
                binding.contactLayout.tvEmail.text = item.email
                binding.contactLayout.rlEmail.setOnClickListener {
                    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", item.email, null))
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "")
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "")
                    startActivity(Intent.createChooser(emailIntent, "Enviar correo..."))
                }
            }
            if (item.address.isNullOrEmpty().not()) {
                binding.contactLayout.rlAddress.visibility = View.VISIBLE
                binding.contactLayout.tvAddressName.text = item.address
            }
            if (item.childrens.isNullOrEmpty().not()) {
                binding.expandingList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
                binding.expandingList.adapter = SubitemAdapter(item.childrens)
            }
        }
    }


    private fun checkAndCallPhone(phoneNumer: String) {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                handleResult(
                        PermissionManager.requestPermissions(
                                this@DetailFragment, 1,
                                Manifest.permission.CALL_PHONE
                        )
                ) { call(phoneNumer) }
            }
        }
    }

    private fun call(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    private fun handleResult(permissionResult: PermissionResult, method: () -> Unit) {
        when (permissionResult) {
            is PermissionResult.PermissionGranted -> {
                method()
            }
            is PermissionResult.PermissionDenied -> {
                Toast.makeText(requireContext(), "Permisos denegados", Toast.LENGTH_SHORT).show()
            }
            is PermissionResult.ShowRational -> {
                showRationaleDialog(permissionResult.requestId, method)
            }
            is PermissionResult.PermissionDeniedPermanently -> {
                Toast.makeText(requireContext(), "Permisos permanentemente denegados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRationaleDialog(requestId: Int, method: () -> Unit) {
        val alertDialog = AlertDialog.Builder(requireContext())
                .setMessage("Por favor otorgue los permisos para realizar la operación solicitada.")
                .setTitle("Aviso!!")
                .setPositiveButton("Otorgar") { _, _ ->
                    when (requestId) {
                        1 -> {
                            coroutineScope.launch(Dispatchers.Main) {
                                handleResult(PermissionManager.requestPermissions(
                                        this@DetailFragment,
                                        1,
                                        Manifest.permission.CALL_PHONE), method)
                            }
                        }
                    }
                }.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }.create()
        alertDialog.show()
    }

    override fun onDestroy() {
        parentJob.cancel()
        super.onDestroy()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnSearch -> {
                findNavController().navigate(R.id.action_navigation_detail_to_searchWordFragment,
                bundleOf(Constants.EXTRA_DATA to data))
            }
        }
    }


}
