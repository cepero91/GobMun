package cu.infocap.gobmun.ui.detail

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.coroutinespermission.PermissionManager
import com.squareup.picasso.Picasso
import cu.infocap.gobmun.R
import cu.infocap.gobmun.domain.model.Data
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.contact_layout.*
import kotlinx.coroutines.*
import java.util.*


class DetailActivity : DaggerAppCompatActivity() {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + parentJob)

    lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.hasExtra("data")) {
            data = intent.getParcelableExtra("data")
        }

        initUI()

    }

    private fun initUI() {
        tv_title.text = data.name
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_detail.text = Html.fromHtml(data.description, Html.FROM_HTML_MODE_LEGACY).trim()
        } else {
            tv_detail.text = Html.fromHtml(data.description).trim()
        }
        val androidColors = resources.getIntArray(R.array.androidcolors)
        val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
        iv_detail.setBackgroundColor(randomAndroidColor)
        if (data.image.isEmpty().not()) {
            Picasso.get().load(data.image).into(iv_detail)
        }
        root.visibility = View.VISIBLE
        rl_primary_phone.visibility = View.GONE
        rl_secundary_phone.visibility = View.GONE
        rl_email.visibility = View.GONE
        rl_address.visibility = View.GONE
        if (data.phone.isNullOrEmpty().not()) {
            rl_primary_phone.visibility = View.VISIBLE
            tv_primary_phone.text = data.phone
            rl_primary_phone.setOnClickListener {
                checkAndCallPhone(data.phone!!)
            }
        }
        if (data.secondaryPhone.isNullOrEmpty().not()) {
            rl_secundary_phone.visibility = View.VISIBLE
            tv_secundary_phone.text = data.secondaryPhone
            rl_secundary_phone.setOnClickListener {
                checkAndCallPhone(data.secondaryPhone!!)
            }
        }
        if (data.email.isNullOrEmpty().not()) {
            rl_email.visibility = View.VISIBLE
            tv_email.text = data.email
            rl_email.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", data.email, null))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "")
                startActivity(Intent.createChooser(emailIntent, "Enviar correo..."))
            }
        }
        if (data.address.isNullOrEmpty().not()) {
            rl_address.visibility = View.VISIBLE
            tv_address_name.text = data.address
        }
        if(data.childrens.isNullOrEmpty().not()){
            expanding_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
            expanding_list.adapter = SubitemAdapter(data.childrens)
        }
    }


    private fun checkAndCallPhone(phoneNumer: String) {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                handleResult(
                        PermissionManager.requestPermissions(
                                this@DetailActivity, 1,
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
                Toast.makeText(applicationContext, "Permisos denegados", Toast.LENGTH_SHORT).show()
            }
            is PermissionResult.ShowRational -> {
                showRationaleDialog(permissionResult.requestId, method)
            }
            is PermissionResult.PermissionDeniedPermanently -> {
                Toast.makeText(applicationContext, "Permisos permanentemente denegados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRationaleDialog(requestId: Int, method: () -> Unit) {
        val alertDialog = AlertDialog.Builder(applicationContext)
                .setMessage("Por favor otorgue los permisos para realizar la operación solicitada.")
                .setTitle("Aviso!!")
                .setPositiveButton("Otorgar") { _, _ ->
                    when (requestId) {
                        1 -> {
                            coroutineScope.launch(Dispatchers.Main) {
                                handleResult(PermissionManager.requestPermissions(
                                        this@DetailActivity,
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


}
