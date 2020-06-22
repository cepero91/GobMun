package cu.infocap.gobmun.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entity (
        @SerializedName("id") val id : Int,
        @SerializedName("municipality") val name : String
) : Parcelable