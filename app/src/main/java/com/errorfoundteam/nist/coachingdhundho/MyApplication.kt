package com.errorfoundteam.nist.coachingdhundho

import android.app.Application
//import com.facebook.FacebookSdk
//import com.facebook.appevents.AppEventsLogger


class MyApplication : Application(){

    companion object{
        @get:Synchronized
        lateinit var instance : MyApplication

    }
    override fun onCreate() {
        super.onCreate()
        instance = this
//        FacebookSdk.getSdkVersion()
       // AppEventsLogger.activateApp(this)
    }
    fun setConnectionListener(listner : InternetCheckBroadcast.ConnectionReceiverListner) {
        InternetCheckBroadcast.connectionReceiverListner = listner
    }

}