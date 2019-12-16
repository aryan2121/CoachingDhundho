package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.your_color)
        }


        Handler().postDelayed({
            //val accessToken = AccessToken.getCurrentAccessToken()
            val acc = GoogleSignIn.getLastSignedInAccount(this)
            if (acc != null || FirebaseAuth.getInstance().currentUser != null) {
                startActivity(Intent(this@SplashActivity,Home0::class.java))
                finish()
            }else{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        },3000)
    }
}
