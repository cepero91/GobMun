package cu.infocap.gobmun.ui.aboutus.viewholder

import androidx.recyclerview.widget.RecyclerView
import cu.infocap.gobmun.databinding.RecyclerAboutUsServiceItemBinding
import cu.infocap.gobmun.ui.aboutus.handler.AboutUsServiceListener
import cu.infocap.gobmun.ui.aboutus.item.AboutUsServiceModel

class AboutUsServiceViewHolder(val binding: RecyclerAboutUsServiceItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(model: AboutUsServiceModel, listener: AboutUsServiceListener?){
        binding.model = model
        binding.listener = listener
        binding.pos = adapterPosition
        binding.executePendingBindings()
    }
}