package com.errorfoundteam.nist.coachingdhundho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_enroll_payment_.*

class Enroll_payment_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enroll_payment_)
        val coaching = intent.getParcelableExtra<uploadInstitute>("PAYMENT")
        textViewPayment.text = coaching.insregistrationfees
    }
}
