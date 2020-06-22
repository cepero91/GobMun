package cu.infocap.gobmun.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cu.infocap.gobmun.domain.model.Data
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import cu.infocap.gobmun.domain.model.DataChild


class SubitemAdapter(var list: List<DataChild>): RecyclerView.Adapter<SubitemViewHolder>(){

    private val expansionsCollection = ExpansionLayoutCollection()
    init {
        expansionsCollection.openOnlyOne(true)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SubitemViewHolder {
        return SubitemViewHolder.create(p0)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: SubitemViewHolder, p1: Int) {
       val data = list[p1]
        p0.bind(data.name,data.description)
        expansionsCollection.add(p0.getExpansionLayout())
    }

    fun setItem(listData: List<DataChild>){
        this.list = listData
        notifyDataSetChanged()
    }

}