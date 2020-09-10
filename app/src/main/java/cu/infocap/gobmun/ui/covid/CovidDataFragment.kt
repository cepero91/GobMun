package cu.infocap.gobmun.ui.covid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.FragmentCovidDataBinding

class CovidDataFragment : Fragment() {

    private lateinit var binding: FragmentCovidDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_covid_data, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.web.loadUrl("https://covid19cubadata.uh.cu/#cuba")
        binding.web.settings.let { it.javaScriptEnabled = true }
    }

}