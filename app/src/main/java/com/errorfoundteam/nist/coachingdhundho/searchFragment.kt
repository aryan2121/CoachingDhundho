package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import java.util.*


class searchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = editText_searchActivity.text.toString()

        editText_searchActivity.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    GettingSearchResult(search)
                    return true
                }
                return false
            }
        })
    }

    private fun GettingSearchResult(search : String){
        val ref = FirebaseDatabase.getInstance().getReference("Institutes")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val searchItem = it.getValue(uploadInstitute::class.java)
                    Log.d("LELELE",it.key)
                    if (searchItem != null){
                        val name = searchItem.insName.toString()
                        if (name.toLowerCase(Locale.UK) == search.toLowerCase(Locale.UK)){
                            Log.d("LELELE","WE got that Search Result")
                        }
                    }
                }
            }

        })
    }



}


