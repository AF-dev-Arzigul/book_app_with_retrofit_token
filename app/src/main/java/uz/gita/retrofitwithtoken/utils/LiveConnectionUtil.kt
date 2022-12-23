package uz.gita.retrofitwithtoken.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest


fun connectivityManager(
    context: Context,
    internetAvailable: () -> Unit,
    internetLost: () -> Unit
) {
    val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    manager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            // ....whenever internet is available, the code inside this block works automatically
            internetAvailable.invoke()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            internetLost.invoke()
        }

    })
}