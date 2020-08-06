package cu.infocap.gobmun.ui.gservice.adapter


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import cu.infocap.gobmun.R
import cu.infocap.gobmun.base.OnItemClickListener
import cu.infocap.gobmun.databinding.RecyclerviewGserviceItemBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.ui.gservice.item.GServiceItem
import kotlinx.android.synthetic.main.recyclerview_gservice_item.view.*
import java.util.*

class GServiceRecyclerViewAdapter(
        private val mValues: List<Data>,
        private val mListener: OnItemClickListener?)
    : androidx.recyclerview.widget.RecyclerView.Adapter<GServiceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = RecyclerviewGserviceItemBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item: Data = mValues[p1]
        p0.bind(item)
    }

    inner class ViewHolder(private val binding: RecyclerviewGserviceItemBinding)
        : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var item: Data? = null

        fun bind(item: Data) {
            this.item = item
            binding.item = item
//            val androidColors = itemView.resources.getIntArray(R.array.androidcolors)
//            val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
//            itemView.iv_menu.setBackgroundColor(randomAndroidColor)
            if(item.image.isNotEmpty()){
                Picasso.get().load(item.image).into(itemView.iv_menu)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mListener?.onItemClick(item)
        }
    }
}