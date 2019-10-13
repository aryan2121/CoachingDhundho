package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.random.Random


class profileFragment : Fragment(), InternetCheckBroadcast.ConnectionReceiverListner {
    private lateinit var database: DatabaseReference
// ...


    //checking Internet Connection
    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        if (isConnected){
            Toast.makeText(context,"connected",Toast.LENGTH_LONG).show()
            //dashboardWithAllHostels()
        }else
            Toast.makeText(context,"Network Not Connected",Toast.LENGTH_LONG).show()
    }//internet connection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val acct = GoogleSignIn.getLastSignedInAccount(context)
        //Implementing the InternetCheckBroadcast
        context?.registerReceiver(InternetCheckBroadcast(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)


        database = FirebaseDatabase.getInstance().reference
        val profile = Random(5561).toString()
        val name = "hero"
        val email = "hero01@gmail.com"


        if (acct != null) {
            profile_name.text = acct.displayName
            if (FirebaseAuth.getInstance().currentUser != null) {
//                textview_paytm_no.visibility = View.VISIBLE
                profile_name.text = FirebaseAuth.getInstance().currentUser!!.phoneNumber
            }
            button_feedback.text = acct.email
            if (acct.photoUrl != null)
                Picasso.get().load("${acct.photoUrl}").placeholder(R.drawable.placeholder_img).into(imageView_profile_image)

        }

        if(FirebaseAuth.getInstance().currentUser != null) {
//            textview_paytm_no.text = FirebaseAuth.getInstance().currentUser!!.phoneNumber
        }



       // val ref = FirebaseDatabase.getInstance().getReference("/Users")
//        val user = User(name, email)
//        database.child("users").child(profile).setValue(user)
//                .addOnSuccessListener {
//                    Log.d("ProfileFragment","Added the user")
//                }
//                .addOnFailureListener {
//                    Log.d("ProfileFragment","Sorry we are unable to upload the user")
//                }
    }


}

