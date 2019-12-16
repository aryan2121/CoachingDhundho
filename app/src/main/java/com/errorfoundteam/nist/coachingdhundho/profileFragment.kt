package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*


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

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val acct = GoogleSignIn.getLastSignedInAccount(context)
        //Implementing the InternetCheckBroadcast
        context?.registerReceiver(InternetCheckBroadcast(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)


        database = FirebaseDatabase.getInstance().reference

        if (acct != null) {
            profile_name.text = acct.displayName
            emailid_profile.text = acct.email
            if (FirebaseAuth.getInstance().currentUser != null) {
                profile_name.text = FirebaseAuth.getInstance().currentUser!!.phoneNumber
            }
            if (acct.photoUrl != null)
                Picasso.get().load("${acct.photoUrl}").placeholder(R.drawable.placeholder_img).into(imageView_profile_image)
        }

        if(FirebaseAuth.getInstance().currentUser != null) {
            emailid_profile.text = FirebaseAuth.getInstance().currentUser!!.phoneNumber
0        }


        //sending Email BY calling email app installed on this device
        coaching_support_team.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse("mailto:?subject=" + "Enquiry for Coaching Dhundo" + "&body=" + "Enter your query/problem " + "&to=" + "coachingdhundo@gmail.com")
            mailIntent.data = data
            try {
                startActivity(Intent.createChooser(mailIntent, "Send mail..."))
                //startActivity(Intent.createChooser(emailIntent, "Send mail..."))
//            finish()
                Log.i("Finished sending email.", "")
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(context,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show()
            }
        }

        logout_profile.setOnClickListener {

            //LoginManager.getInstance().logOut()
            FirebaseAuth.getInstance().signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient = GoogleSignIn.getClient(activity!!.baseContext, gso)
            googleSignInClient.signOut()
            val intent = Intent(context,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        button_feedback.setOnClickListener {
            val intent = Intent(context,FeedbackActivtiy::class.java)
            startActivity(intent)
        }
    }
}

