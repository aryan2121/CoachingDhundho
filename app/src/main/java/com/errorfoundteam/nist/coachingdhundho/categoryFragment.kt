package com.errorfoundteam.nist.coachingdhundho

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_category.*
import kotlin.math.log


class categoryFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_bank.setOnClickListener {

            val ref = FirebaseDatabase.getInstance().getReference("/Courses/")
            ref.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        val bank = it.getValue(uploadcompetitiveexams::class.java)
                        Log.d("LOLOLO","Its here")
                        Log.d("LOLOLO",it.key)
                        if (bank != null){
                            Log.d("LOLOLO","HELLO")
                            if (bank.banking.isNotEmpty()){
                                Log.d("LOLOLO",it.key)
                            }
                        }
                    }
                }
            })

            
        }
        card_catandxat.setOnClickListener {

        }
        card_computer.setOnClickListener {

        }
        card_ssc.setOnClickListener {

        }
        card_upsc.setOnClickListener {

        }
    }

}

