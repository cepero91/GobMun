package cu.infocap.gobmun.ui.aboutus.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AboutUsServiceModel(
        val id: Int,
        val title: String,
        val description: String,
        val icon: Int
) : Parcelable