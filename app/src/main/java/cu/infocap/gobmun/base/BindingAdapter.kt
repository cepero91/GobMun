package cu.infocap.gobmun.base

import android.databinding.BindingAdapter
import android.graphics.Color
import android.widget.ImageView
import android.R.array
import java.util.*


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageResource")
    fun loadImageResource(imageView: ImageView, sourceId: Int) {
        imageView.setImageResource(sourceId)
    }

}