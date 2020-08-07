package cu.infocap.gobmun.ui.aboutus

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.FragmentAboutUsBinding
import cu.infocap.gobmun.ui.aboutus.adapter.AboutUsServiceListAdapter
import cu.infocap.gobmun.ui.aboutus.handler.AboutUsServiceListener
import cu.infocap.gobmun.ui.aboutus.item.AboutUsServiceModel

class AboutUsFragment : Fragment(), AboutUsServiceListener {

    private lateinit var binding: FragmentAboutUsBinding

    private val list: List<AboutUsServiceModel> by lazy {
        listOf(
                AboutUsServiceModel(1, resources.getStringArray(R.array.services_titles)[0], resources.getStringArray(R.array.services_descriptions)[0], R.drawable.ic_reparacion),
                AboutUsServiceModel(2, resources.getStringArray(R.array.services_titles)[1], resources.getStringArray(R.array.services_descriptions)[1], R.drawable.ic_connectividad),
                AboutUsServiceModel(3, resources.getStringArray(R.array.services_titles)[2], resources.getStringArray(R.array.services_descriptions)[2], R.drawable.ic_desarrollo),
                AboutUsServiceModel(4, resources.getStringArray(R.array.services_titles)[3], resources.getStringArray(R.array.services_descriptions)[3], R.drawable.ic_venta)
        )
    }

    private val adapter: AboutUsServiceListAdapter by lazy {
        AboutUsServiceListAdapter(list, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_about_us, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.serviceList.adapter = adapter
    }

    override fun onServiceClick(pos: Int, data: AboutUsServiceModel) {
        Toast.makeText(requireContext(), "Under development", Toast.LENGTH_SHORT).show()
    }

}