package cu.infocap.gobmun.domain.model

import com.google.gson.annotations.SerializedName

data class BaseData (
        @SerializedName("status") val status : Int,
        @SerializedName("success") val success : Boolean,
        @SerializedName("data") val data : List<Data>,
        @SerializedName("pagination") val pagination : Pagination
)