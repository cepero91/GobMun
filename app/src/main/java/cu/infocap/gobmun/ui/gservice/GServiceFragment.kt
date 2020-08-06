package cu.infocap.gobmun.ui.gservice


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.BaseFragment
import cu.infocap.gobmun.databinding.FragmentGserviceListBinding
import cu.infocap.gobmun.ui.gservice.adapter.GServiceRecyclerViewAdapter
import cu.infocap.gobmun.ui.gservice.viewmodel.GServiceViewModel
import cu.infocap.gobmun.util.Constants
import kotlinx.android.synthetic.main.fragment_gservice_list.*
import javax.inject.Inject


class GServiceFragment : BaseFragment(), androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener {

    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: GServiceViewModel

    private lateinit var binding: FragmentGserviceListBinding

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = activity!!.getSharedPreferences(Constants.SHARED, Context.MODE_PRIVATE)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_gservice_list, container, false)

        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GServiceViewModel::class.java)
        swipe.setOnRefreshListener(this)
        viewModel.loadService(sharedPreferences.getString(Constants.ENTITYID, "") ?: "")
        loadItems()
    }

    private fun loadItems() {
        swipe.isRefreshing = true
        viewModel.gserviceList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it!!.data != null) {
                binding.list.adapter = GServiceRecyclerViewAdapter(it.data!!, listener)
            }
            swipe.isRefreshing = false
        })
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                GServiceFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    override fun onRefresh() {
        viewModel.loadService(sharedPreferences.getString(Constants.ENTITYID, "")!!)
    }
}
