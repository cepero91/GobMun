package cu.infocap.gobmun.domain.model

import com.google.gson.annotations.SerializedName

data class Pagination (
        @SerializedName("count") val count : Int,
        @SerializedName("total") val total : Int,
        @SerializedName("perPage") val perPage : Int,
        @SerializedName("currentPage") val currentPage : Int,
        @SerializedName("totalPages") val totalPages : Int
//        @SerializedName("links") val links : List<String>
)