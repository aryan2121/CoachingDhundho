package com.errorfoundteam.nist.coachingdhundho

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_details_phone_login.*
import kotlinx.android.synthetic.main.activity_details_phone_login.view.*
import kotlinx.android.synthetic.main.layout_placeholder_recycleritems.*

class DetailsPhoneLogin : AppCompatActivity() {

    private lateinit var gender: String

    val Database = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_phone_login)
        val phoneNo = intent.getStringExtra("phoneNumber")

        details_phone_submit.setOnClickListener {
            if (name_details_phoneLogin.text.toString().length < 5) {
                name_details_phoneLogin.error = "Enter full name"
            } else {
                if (gender.isEmpty()){
                    male_details_phoneLogin.error = "Select your Gender"
                }else {
                    val userInfo = SaveUserInfo(name_details_phoneLogin.text.toString(),phoneNo,gender)
                    val myRef = Database.getReference("UserInfo")
                    myRef.child(name_details_phoneLogin.text.toString()).setValue(userInfo)         // Uploading user data to firebse with tag name
                    val listner = object  :ValueEventListener{
                        override fun onCancelled(p0: DatabaseError) {
                            Log.d("DetailsPhoneLogin",p0.details)           //if it gets stuck sumewhere
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            Log.d("DetailsPhoneLogin",p0.key)                  // if it gets succesfully added to firebase
                            val intent = Intent(this@DetailsPhoneLogin,Home0::class.java)
                            startActivity(intent)
                        }
                    }
                    myRef.addValueEventListener(listner)
                }
            }

        }

        fun onClick(view: View) {
            if (view == male_details_phoneLogin) {
                gender = "male"
                male_details_phoneLogin.alpha = 1F
                female_details_phoneLogin.alpha = 0.5F
            }
            if (view == female_details_phoneLogin) {
                gender = "female"
                male_details_phoneLogin.alpha = 0.5F
                female_details_phoneLogin.alpha = 1F
            }

        }
    }
}
