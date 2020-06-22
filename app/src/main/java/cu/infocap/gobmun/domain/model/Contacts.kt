package cu.infocap.gobmun.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contacts(
        @SerializedName("id") val id: Int,
        @SerializedName("phone") val phone: String?,
        @SerializedName("secondary_phone") val secondary_phone: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("address") val address: String?
) : Parcelable