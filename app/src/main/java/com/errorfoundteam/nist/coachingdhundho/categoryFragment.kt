package com.errorfoundteam.nist.coachingdhundho

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        imageView_ads1.setOnClickListener{
            Toast.makeText(context,"Please Contact Admin For Ads",Toast.LENGTH_SHORT).show()
        }
        imageView_ads2.setOnClickListener{
            Toast.makeText(context,"Please Contact Admin For Ads",Toast.LENGTH_SHORT).show()
        }
        imageView_ads3.setOnClickListener{
            Toast.makeText(context,"Please Contact Admin For Ads",Toast.LENGTH_SHORT).show()
        }
        imageView_ads4.setOnClickListener{
            Toast.makeText(context,"Please Contact Admin For Ads",Toast.LENGTH_SHORT).show()
        }
        imageView_ads5.setOnClickListener{
            Toast.makeText(context,"Please Contact Admin For Ads",Toast.LENGTH_SHORT).show()
        }

    }

}

