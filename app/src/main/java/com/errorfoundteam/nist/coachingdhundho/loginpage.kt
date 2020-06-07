package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_loginpage.*
import java.util.concurrent.TimeUnit
import android.content.IntentFilter
import android.os.CountDownTimer
import android.net.ConnectivityManager


class loginpage : AppCompatActivity(), InternetCheckBroadcast.ConnectionReceiverListner {
    lateinit var mCallBacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    var verificationId = ""
    var code = ""
    lateinit var cTimer : CountDownTimer



//checking Internet Connection
    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        if (isConnected){
            Toast.makeText(this,"connected",Toast.LENGTH_LONG).show()
            //dashboardWithAllHostels()
        }else
            Toast.makeText(this,"Network Not Connected",Toast.LENGTH_LONG).show()
    }//internet connection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        //Implementing the InternetCheckBroadcast
        baseContext.registerReceiver(InternetCheckBroadcast(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)


        val phoneNumber = intent.getStringExtra("phoneNumber")
        mAuth = FirebaseAuth.getInstance()
        textView4.text = "+91 $phoneNumber"
        verifyPhoneNumber(phoneNumber!!)

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.your_color)
        }

        login.setOnClickListener {
            verifycode(editText_otp.text.toString())
        }
        button_back_onloginpage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


    private fun verifyPhoneNumber(number: String) {

        verificationcallbacks()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBacks
        )
    }
    private fun verificationcallbacks(){//starting of verificationcallbacks function
        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationFailed(p0: FirebaseException) {
                Log.w("MainActivity","Failed",p0)
                Toast.makeText(this@loginpage, p0.localizedMessage, Toast.LENGTH_LONG).show()
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("MainActivity","Succeded")
                code = credential.smsCode.toString()
                signIn(credential)
                //calls a function
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId = p0
            }

        }

    }
    //closing of verificationcallbacks function

    private fun verifycode(code :String){

        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code)
        signIn(credential)

    }//cheking th verification code


    //checking the credetials of user is correct or not
    private fun  signIn(credential: PhoneAuthCredential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful){
//                        val account = GoogleSignIn.getLastSignedInAccount(this)
                        editText_otp.setText("${credential.smsCode}")

                        Toast.makeText(this,"phone number added",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,DetailsPhoneLogin::class.java))
                        finish()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Something went wrong ! try again",Toast.LENGTH_LONG).show()
                }
    }
}
