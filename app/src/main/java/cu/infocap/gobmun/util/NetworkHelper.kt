package cu.infocap.gobmun.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkHelper @Inject constructor(val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}