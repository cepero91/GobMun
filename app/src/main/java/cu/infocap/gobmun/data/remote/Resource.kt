package cu.infocap.gobmun.data.remote

enum class Status {
    SUCCESS,
    ERROR
}

data class Resource<out T>(
        val status: Status,
        val data: T?,
        val throwable: Throwable?
)