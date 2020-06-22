package cu.infocap.gobmun.ui.gservice


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.BaseFragment
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.FragmentGprocedureListBinding
import cu.infocap.gobmun.databinding.FragmentGserviceListBinding
import cu.infocap.gobmun.ui.gservice.adapter.GServiceRecyclerViewAdapter
import cu.infocap.gobmun.ui.gservice.item.GServiceItem
import cu.infocap.gobmun.ui.gservice.viewmodel.GServiceViewModel
import cu.infocap.gobmun.util.Constants
import kotlinx.android.synthetic.main.fragment_gservice_list.*
import java.util.*
import javax.inject.Inject


class GServiceFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener  {

    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: GServiceViewModel

    private lateinit var binding: FragmentGserviceListBinding

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = activity!!.getSharedPreferences(Constants.SHARED, Context.MODE_PRIVATE)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GServiceViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_gservice_list, container, false)

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
        swipe.setOnRefreshListener(this)
        viewModel.loadService(sharedPreferences.getString(Constants.ENTITYID,""))
        loadItems()
    }

    private fun loadItems() {
        swipe.isRefreshing = true
        viewModel.gserviceList.observe(this, android.arch.lifecycle.Observer {
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
        viewModel.loadService(sharedPreferences.getString(Constants.ENTITYID,"")!!)
    }
}
