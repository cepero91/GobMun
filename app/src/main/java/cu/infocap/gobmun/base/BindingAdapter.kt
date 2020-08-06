package cu.infocap.gobmun.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("loadImageResource")
fun loadImageResource(imageView: ImageView, sourceId: Int) {
    imageView.setImageResource(sourceId)
}