package cu.infocap.gobmun.ui.aboutus.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cu.infocap.gobmun.databinding.RecyclerAboutUsServiceItemBinding
import cu.infocap.gobmun.ui.aboutus.handler.AboutUsServiceListener
import cu.infocap.gobmun.ui.aboutus.item.AboutUsServiceModel
import cu.infocap.gobmun.ui.aboutus.viewholder.AboutUsServiceViewHolder

class AboutUsServiceListAdapter(
        private val services: List<AboutUsServiceModel>,
        private val listener: AboutUsServiceListener?)
    : RecyclerView.Adapter<AboutUsServiceViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AboutUsServiceViewHolder {
        val binding = RecyclerAboutUsServiceItemBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return AboutUsServiceViewHolder(binding)
    }

    override fun getItemCount(): Int = services.size

    override fun onBindViewHolder(p0: AboutUsServiceViewHolder, p1: Int) {
        val item: AboutUsServiceModel = services[p1]
        p0.bind(item, listener)
    }
}