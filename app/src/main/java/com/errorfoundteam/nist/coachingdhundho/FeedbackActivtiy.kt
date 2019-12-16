package com.errorfoundteam.nist.coachingdhundho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_feedback_activtiy.*

class FeedbackActivtiy : AppCompatActivity() {
    lateinit var name : String
    lateinit var id :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_activtiy)
        val acc = GoogleSignIn.getLastSignedInAccount(this)
        if (acc != null){
             name = acc.displayName.toString()
             id = acc.id.toString()
        }
//        else if (Profile.getCurrentProfile() != null) {
//            name = Profile.getCurrentProfile().name.toString()
//            id = Profile.getCurrentProfile().id.toString()
//        }


        submit_feedback.setOnClickListener {

            if (editText_feedbak.text.isNotEmpty()) {
                val chillld = "$name+$id"
                val ref = FirebaseDatabase.getInstance().getReference("Feedback")
                val feedbackUploadToFirebase = FeedbackUpload(
                        editText_feedbak.text.toString()
                )
                ref.child(chillld).setValue(feedbackUploadToFirebase).addOnCompleteListener {
                    Toast.makeText(this, "Feedback Sent", Toast.LENGTH_SHORT).show()
                    editText_feedbak.text.clear()
                }
            }else
                Toast.makeText(this,"Please enter your responce", Toast.LENGTH_SHORT).show()
        }
    }
}
