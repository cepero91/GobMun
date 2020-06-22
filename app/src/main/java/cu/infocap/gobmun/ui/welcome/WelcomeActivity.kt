package cu.infocap.gobmun.ui.welcome

import android.arch.lifecycle.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.ActivityWelcomeBinding
import cu.infocap.gobmun.ui.home.BottomActivity
import cu.infocap.gobmun.ui.home.HomeActivity
import cu.infocap.gobmun.ui.welcome.item.MunSearchModel
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.activity_welcome.*
import android.widget.Toast
import cu.infocap.gobmun.ui.gservice.viewmodel.GServiceViewModel
import cu.infocap.gobmun.util.Constants
import cu.infocap.gobmun.util.NetworkHelper
import dagger.android.support.DaggerAppCompatActivity
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat
import javax.inject.Inject


class WelcomeActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private var listMun: ArrayList<MunSearchModel> = ArrayList()

    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkHelper: NetworkHelper

    private lateinit var viewModel: WelcomeViewModel
    private var dialogLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        sharedPreferences = getSharedPreferences(Constants.SHARED, Context.MODE_PRIVATE)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)

        frameLayout.setOnClickListener {
            if (networkHelper.isNetworkAvailable()) {
                if (!dialogLoading) {
                    loadEntity()
                }
            } else {
                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            if (sharedPreferences.getString(Constants.ENTITYID, "") != null &&
                    !sharedPreferences.getString(Constants.ENTITYID, "")!!.isEmpty()) {
                goToHomeActivity()
            } else {
                Toast.makeText(this, getString(R.string.valid_msg), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadEntity() {
        dialogLoading = true
        progressBar.visibility = View.VISIBLE
        viewModel.loadEntity().observe(this@WelcomeActivity, Observer {
            if (it!!.data != null) {
                listMun = ArrayList()
                it.data!!.forEach { dat ->
                    listMun.add(MunSearchModel(dat.id.toString(), dat.name))
                }
                showDialog(listMun)
            } else {
                Toast.makeText(this, "Un problema ha ocurrido. Intente luego.", Toast.LENGTH_SHORT).show()
            }
            progressBar.visibility = View.GONE
            dialogLoading = false
        })
    }

    private fun showDialog(list: ArrayList<MunSearchModel>) {
        val dialogCompat = SimpleSearchDialogCompat(this, getString(R.string.municipalities),
                getString(R.string.dialog_msg), null, list,
                SearchResultListener<MunSearchModel> { dialog, item, _ ->
                    tv_mun_selected.text = item.title
                    sharedPreferences.edit().putString(Constants.ENTITYID, item.id).apply()
                    dialog.dismiss()
                })
        dialogCompat.show()
    }

    private fun goToHomeActivity() {
        startActivity(Intent(this, BottomActivity::class.java))
        finish()
    }


}
