package cu.infocap.gobmun.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.FragmentAboutServiceDetailBinding
import cu.infocap.gobmun.ui.aboutus.item.AboutUsServiceModel
import cu.infocap.gobmun.util.Constants
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AboutServiceDetailFragment : DaggerFragment() {

    private lateinit var binding: FragmentAboutServiceDetailBinding
    private var service: AboutUsServiceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            service = it.getParcelable(Constants.EXTRA_ABOUT_SERVICE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_about_service_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        service?.let {
            binding.model = it
        }
        binding.executePendingBindings()
    }

}