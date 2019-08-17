package com.example.user.coachingdhundho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_filter.*

class filterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        clear_id.setOnClickListener {
            startActivity(Intent(this,filterActivity::class.java))


        }

        spinner_id.onItemSelectedListener =

                object: AdapterView.OnItemSelectedListener{

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                        if(p2>0)

                            Toast.makeText(this@filterActivity,

                                    spinner_id.selectedItem.toString(),

                                    Toast.LENGTH_LONG).show()



                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {



                    }

                }
    }
}
