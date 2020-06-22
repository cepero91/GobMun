package cu.infocap.gobmun.ui.gprocedure

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
import android.widget.Toast
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.BaseFragment
import cu.infocap.gobmun.base.BaseItem
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.commons.ViewModelFactory
import cu.infocap.gobmun.databinding.FragmentGprocedureListBinding
import cu.infocap.gobmun.ui.gprocedure.adapter.GProcedureRecyclerViewAdapter

import cu.infocap.gobmun.ui.gprocedure.item.GProcedureItem
import cu.infocap.gobmun.ui.gprocedure.viewmodel.GProcedureViewModel
import cu.infocap.gobmun.util.Constants
import kotlinx.android.synthetic.main.fragment_gprocedure_list.*
import java.util.*
import javax.inject.Inject


class GProcedureFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GProcedureViewModel

    private lateinit var binding: FragmentGprocedureListBinding

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = activity!!.getSharedPreferences(Constants.SHARED, Context.MODE_PRIVATE)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(GProcedureViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(container?.context), R.layout.fragment_gprocedure_list, container, false)

        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe.setOnRefreshListener(this)
        viewModel.loadProcedure(sharedPreferences.getString(Constants.ENTITYID,"")!!)
        loadItems()
    }

    private fun loadItems() {
        swipe.isRefreshing = true
        viewModel.gprocedureList.observe(this,android.arch.lifecycle.Observer {
            if(it!!.data!=null){
                binding.list.adapter = GProcedureRecyclerViewAdapter(it.data!!, listener)
            }
            swipe.isRefreshing = false
        })
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                GProcedureFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    override fun onRefresh() {
        viewModel.loadProcedure(sharedPreferences.getString(Constants.ENTITYID,"")!!)
    }
}
