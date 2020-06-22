package cu.infocap.gobmun.ui.detail

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cu.infocap.gobmun.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.recycler_expandable_cell.view.*
import com.github.florent37.expansionpanel.ExpansionLayout



class SubitemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(name: String?, description: String?) {
        itemView.title.text = name
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView.description.text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            itemView.description.text = Html.fromHtml(description)
        }
        itemView.expansionLayout.collapse(false)
    }

    fun getExpansionLayout(): ExpansionLayout {
        return itemView.expansionLayout
    }

    companion object {
        fun create(parent: ViewGroup): SubitemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_expandable_cell, parent, false)
            return SubitemViewHolder(view)
        }
    }
}