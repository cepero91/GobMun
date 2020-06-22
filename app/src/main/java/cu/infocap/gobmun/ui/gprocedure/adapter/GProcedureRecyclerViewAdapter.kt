package cu.infocap.gobmun.ui.gprocedure.adapter

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.DrawableUtils
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.RecyclerviewGprocedureItemBinding
import cu.infocap.gobmun.domain.model.Data

import cu.infocap.gobmun.ui.gprocedure.item.GProcedureItem

import kotlinx.android.synthetic.main.recyclerview_gprocedure_item.view.*
import java.util.*

class GProcedureRecyclerViewAdapter(
        private val mValues: List<Data>,
        private val mListener: OnItemClickListener?)
    : RecyclerView.Adapter<GProcedureRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewGprocedureItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(private val binding: RecyclerviewGprocedureItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var item: Data? = null

        fun bind(item: Data){
            this.item = item
            binding.item = item
            if(item.image.isNotEmpty()){
                Picasso.get().load(item.image).placeholder(ColorDrawable(Color.parseColor("#BDBDBD"))).into(itemView.iv_menu)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mListener?.onItemClick(item)
        }
    }
}
