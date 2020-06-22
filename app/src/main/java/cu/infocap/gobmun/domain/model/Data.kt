package cu.infocap.gobmun.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data (
        @SerializedName("id") val id : Int,
        @SerializedName("uui") val uui : String,
        @SerializedName("name") val name : String,
        @SerializedName("description") val description : String,
        @SerializedName("image") val image : String,
        @SerializedName("phone") val phone : String?,
        @SerializedName("secondary_phone") val secondaryPhone : String?,
        @SerializedName("email") val email : String?,
        @SerializedName("address") val address : String?,
        @SerializedName("childrens") val childrens : List<DataChild> = listOf()
) : Parcelable