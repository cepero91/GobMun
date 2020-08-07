package cu.infocap.gobmun.base

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("loadImageResource")
fun loadImageResource(imageView: ImageView, sourceId: Int) {
    imageView.setImageResource(sourceId)
}

@BindingAdapter("imageDrawable")
fun imageDrawable(imageView: AppCompatImageView, sourceId: Int) {
    imageView.setImageResource(sourceId)
}