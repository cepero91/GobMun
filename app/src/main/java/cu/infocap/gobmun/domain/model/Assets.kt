package cu.infocap.gobmun.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Assets(
        @SerializedName("id") val id: Int,
        @SerializedName("file") val file: String,
        @SerializedName("url") val url: String?,
        @SerializedName("type") val type: String,
        @SerializedName("post_id") val postId: Int,
        @SerializedName("service_id") val serviceId: Int
) : Parcelable