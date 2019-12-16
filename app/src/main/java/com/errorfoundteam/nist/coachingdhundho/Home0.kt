package com.errorfoundteam.nist.coachingdhundho

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home0.*

class Home0 : AppCompatActivity(), InternetCheckBroadcast.ConnectionReceiverListner {


    //checking Internet Connection
    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        if (isConnected){
            Toast.makeText(this,"connected", Toast.LENGTH_LONG).show()
            //dashboardWithAllHostels()
        }else
            Toast.makeText(this,"Network Not Connected", Toast.LENGTH_LONG).show()
    }//internet connection


    //now create five fragment as create in java
    lateinit var hhomeFragment: homeFragment
    lateinit var ssearchFragment: searchFragment
    lateinit var nnoticeFragment: notificationFragment
    lateinit var ccategoryFragment: categoryFragment
    lateinit var pprofileFragment: profileFragment


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        //        when (item.itemId) {


    when(item.itemId){
    //now add fragment
        R.id.home -> {
            hhomeFragment = homeFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout,hhomeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

        }
        R.id.search -> {
            ssearchFragment = searchFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout,ssearchFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

        }
        R.id.category -> {
            ccategoryFragment = categoryFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout,ccategoryFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

        }
        R.id.notifications -> {
            nnoticeFragment = notificationFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout,nnoticeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

        }

        R.id.profile -> {
            pprofileFragment = profileFragment()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout,pprofileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

        }


    }
    true
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home0)

        //Implementing the InternetCheckBroadcast
        baseContext.registerReceiver(InternetCheckBroadcast(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)

        hhomeFragment = homeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.framelayout,hhomeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



//changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.your_color)
        }

    }
}






//{

//
//    var HomeFragment: homeFragment = homeFragment()
//    lateinit var SearchFragment: searchFragment
//    lateinit var CategoryFragment: categoryFragment
//    lateinit var NotificationFragment: notificationFragment
//    lateinit var ProfileFragment: profileFragment
//
//
//
//
//   private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.home -> {
//                HomeFragment = homeFragment()
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.framelayout,HomeFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//            }
//
//            R.id.search -> {
//                SearchFragment = searchFragment()
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.framelayout,SearchFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//            }
//
//            R.id.category -> {
//                CategoryFragment = categoryFragment()
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.framelayout,CategoryFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//            }
//
//            R.id.notifications -> {
//                NotificationFragment = notificationFragment()
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.framelayout,NotificationFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//            }
//
//            R.id.profile -> {
//                ProfileFragment = profileFragment()
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.framelayout,ProfileFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//            }
//
//
//
//
//
//
//
//
//
//
//        }
//        false
//   }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home0)
//        HomeFragment = homeFragment()
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.framelayout,HomeFragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commit()
//
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//
//
//
//
//    }
//
//private var doubleBackToExitPressedOnce =false
//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce){
//        super.onBackPressed()
//        finish()}
//    this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this,"Please click Back again to exit",Toast.LENGTH_SHORT).show()
//        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce=false },2000)
//    }
//}
