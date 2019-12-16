package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //lateinit var callbackManager : CallbackManager
    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var googleSignOption : GoogleSignInOptions
    lateinit var callbackManager : CallbackManager
    val Unique_Code : Int = 1

    var googleClicked = false
    var facebookClicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        FacebookSdk.sdkInitialize(this)
//        FacebookSdk.getSdkVersion()
//        val loginButton = findViewById<LoginButton>(R.id.button_facebook)
//        callbackManager = CallbackManager.Factory.create()
//        val EMAIL = "email"
//       loginButton.setPermissions(Arrays.asList(EMAIL))

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.your_color)
        }

        next.setOnClickListener {
            if (editText_phoneNumber.text.isNotEmpty() && editText_phoneNumber.text.length == 10) {
                val i = Intent(this, loginpage::class.java)
                i.putExtra("phoneNumber", editText_phoneNumber.text.toString())
                startActivity(i)
            }else
                Toast.makeText(this,"Please Enter a valid Number",Toast.LENGTH_SHORT).show()
        }


//        button_facebook.setOnClickListener {
//            loginButton.performClick()
//        }


        button_gmail.setOnClickListener {
            googleClicked = true
            facebookClicked = false
            googleSignOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignOption)
            signInWithGoogle()
//            pogressbar_facebooklogin_page.visibility = View.VISIBLE
//            loginButton.visibility = View.INVISIBLE
//            button_facebookk.visibility = View.INVISIBLE
//            button_gmail.visibility = View.INVISIBLE
        }



        //loginbutton on cick perform
        // Callback registration
//        loginButton.setOnClickListener {
//            googleClicked = false
//            facebookClicked = true
//            //pogressbar_facebooklogin_page.visibility = View.VISIBLE
//            loginButton.visibility = View.INVISIBLE
//            button_gmail.visibility = View.INVISIBLE
//            button_facebook.visibility = View.INVISIBLE
////            callbackManager = CallbackManager.Factory.create()
////            LoginManager.getInstance().logInWithReadPermissions(this, asList("email"))
//            /*LoginManager.getInstance()*/loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//
//            override fun onSuccess(loginResult: LoginResult) {
////                val  phoneNumberrr = "EmptyNow"
////                val chillld = Profile.getCurrentProfile().name + Profile.getCurrentProfile().id
////                val reff = FirebaseDatabase.getInstance().getReference("/UserData")
////                val userDatatoFirebase = SavingUserData(
////                        Profile.getCurrentProfile().name,
////                        Profile.getCurrentProfile().id.toString(),
////                        phoneNumberrr
////                )
////
////                reff.child(chillld).setValue(userDatatoFirebase).addOnCompleteListener {
////                    Log.d("GmailRegistration","User Uploaded Successfully")
////                }
//
//                val i = Intent(this@MainActivity, Home0::class.java)
//                Log.d("LOGIN",loginResult.accessToken.toString())
//                startActivity(i)
//            }
//
//            override fun onCancel() {
//                //pogressbar_facebooklogin_page.visibility = View.INVISIBLE
//                button_facebook.visibility = View.VISIBLE
//                button_gmail.visibility = View.VISIBLE
//                Toast.makeText(this@MainActivity, "Login Cancelled", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onError(exception: FacebookException) {
//
//               // pogressbar_facebooklogin_page.visibility = View.INVISIBLE
//                button_facebook.visibility = View.VISIBLE
//                button_gmail.visibility = View.VISIBLE
//                // App code
//                Toast.makeText(this@MainActivity,"Error : $exception",Toast.LENGTH_SHORT).show()
//            }
//        })
//        }


    }
    private fun signInWithGoogle(){
        val signInIntent : Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent , Unique_Code)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (googleClicked) {
            if (requestCode == Unique_Code) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleResult(task)
            }
        }

    }


    private fun  handleResult(taskCompleted: Task<GoogleSignInAccount>){
        try {
            val account  = taskCompleted.getResult(ApiException::class.java)
            Log.d("GmailRegistration","handle Result")
            Log.d("GmailRegistration", "$account")
//            val  phoneNumberrr = "EmptyNow"
//            val chillld = account!!.displayName + account.id
//            val ref = FirebaseDatabase.getInstance().getReference("/UserData")
//            val UploaduserDatatoFirebase = SavingUserData(
//                    account.displayName.toString(),
//                    account.email.toString(),
//                    phoneNumberrr
//            )

//            ref.child(chillld).setValue(UploaduserDatatoFirebase).addOnCompleteListener {
//                Log.d("GmailRegistration","User Uploaded Successfully")
//            }

            val intent = Intent(this, Home0::class.java)
            startActivity(intent)
            finish()

        }catch (e : ApiException){
           // pogressbar_facebooklogin_page.visibility = View.INVISIBLE
            Log.d("GmailRegistration","$e")
            Toast.makeText(this, "Login Cancelled", Toast.LENGTH_LONG).show()
            //button_facebookk.visibility = View.VISIBLE
          //  button_gmail.visibility = View.VISIBLE
        }
    }




}
