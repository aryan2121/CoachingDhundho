package com.errorfoundteam.nist.coachingdhundho

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class InternetCheckBroadcast : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val isconnected = connectionCheck(context)
        if (connectionReceiverListner != null)
            connectionReceiverListner!!.onNetworkConnectionChanged(isconnected)
    }

    interface ConnectionReceiverListner {
        fun onNetworkConnectionChanged(isConnected : Boolean)
    }

    companion object{
        var connectionReceiverListner : ConnectionReceiverListner ?= null
        val isConnected : Boolean
            get() {

                val cm = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return (activeNetwork != null &&activeNetwork.isConnected)
            }
    }
    private fun connectionCheck(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return (activeNetwork != null &&activeNetwork.isConnected)
    }

}