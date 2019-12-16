package com.errorfoundteam.nist.coachingdhundho

import android.content.Context
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
import android.widget.Toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_view_holder_home.view.*
import java.util.*


class searchFragment : Fragment() {
    val cource = "upsc"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        val searchData = "SEARCHDATA"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val name  = activity!!.intent.getStringArrayExtra("ARRAYS")
//        Log.d("LELELE",name.elementAt(5))

        val country = arrayOf("lalpur", "jail more")[(Math.random() * 2).toInt()]

        Toast.makeText(context,country,Toast.LENGTH_LONG).show()
        // val search = editText_searchActivity.text.toString()
        val adapter = GroupAdapter<ViewHolder>()
        recyclerview_searchPage.adapter = adapter
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())
        adapter.add(placeholder())

        GettingShortResults(country)

//        editText_searchActivity.setOnEditorActionListener(object : TextView.OnEditorActionListener {
//            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
//                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
//                    GettingSearchResult(search)
//                    return true
//                }
//                return false
//            }
//        })
    }


    // getting search reasult from firebase
    private fun GettingSearchResult(search: String) {
        val ref = FirebaseDatabase.getInstance().getReference("/Courses/") //calling cources tag from firebase
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>() // creating an adapter to use for data settling

                p0.children.forEach {
                    val keyName = it.key             //taking only the key(name) of institute from cources tag
                    val reff = FirebaseDatabase.getInstance().getReference("/Courses/$keyName/") //entering the keyname tag inside cources tag
                    reff.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            p0.children.forEach {
                                val competitive = it.getValue(uploadcompetitiveexams::class.java)
                                Log.d("TRUEE", it.value.toString())
                                if (it.value.toString() == "upsc") {
                                    Log.d("TTTT", "Congrats")
                                }
                                if (competitive != null) {
                                    Log.d("TREE", it.key)
                                    // Log.d("TREE",competitive.)
                                    if (competitive.upsc.isNotEmpty()) {
                                        Log.d("TRUEE", competitive.upsc) //we got the cources list ehich ca be checked
                                        val refff = FirebaseDatabase.getInstance().getReference("/Institutes/") //entering the keyname tag inside cources tag
                                        refff.addValueEventListener(object : ValueEventListener {
                                            override fun onCancelled(p0: DatabaseError) {
                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                            }

                                            override fun onDataChange(p0: DataSnapshot) {
                                                p0.children.forEach {
                                                    val bhejRhe = it.getValue(uploadInstitute::class.java)
                                                    if (bhejRhe!!.insName == keyName) {
                                                        Log.d("TREE", bhejRhe.insName)
                                                        adapter.add(SearchAddingData(bhejRhe))

                                                    }
                                                }
                                            }
                                        })
                                    }
                                }
                                adapter.setOnItemClickListener { item, view ->
                                    val coachingItem = item as SearchAddingData
                                    Toast.makeText(context, "how dare you touched me", Toast.LENGTH_SHORT).show()
                                    val i = Intent(context, openInstitute::class.java)
                                    i.putExtra(searchData, coachingItem.str)
                                    i.putExtra("SEARCHKEY", "SEARCH")
                                    i.putExtra("HOMEKEY", "")
                                    startActivity(i)
                                }
                            }
                        }
                    })
                }
                recyclerview_searchPage.adapter = adapter
            }
        })
    }


    private fun GettingShortResults(country :String) {
        Log.d("SHORT",country)

        val ref = FirebaseDatabase.getInstance().getReference("/Institutes/")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context,"Sorry Some Error Occurs",Toast.LENGTH_SHORT).show()
            }


            override fun onDataChange(p0: DataSnapshot) {
                val  intent = Intent(context,openInstitute::class.java)

                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    val sime = it.childrenCount
                    Log.d("SHORT",sime.toString())

                    val coaching= it.getValue(uploadInstitute::class.java)
                    Log.d("SHORT", it.toString())

                    adapter.setOnItemClickListener { item, view ->
                        val coachingItem = item as Order
                        intent.putExtra("SEARCHKEY", "")
                        intent.putExtra("HOMEKEY", "HOMEKEY")
                        intent.putExtra(homeFragment.Coaching_Key, coachingItem.coaching)
                        startActivity(intent)

                    }
                    if (coaching != null)
                        if (coaching.city.toLowerCase(Locale.UK) == country.toLowerCase(Locale.UK))
                            adapter.add(Order(coaching,requireContext()))
                    else
                            Log.d("SHORT", "Bhaisahab tu rest kr")

                    Log.d("SHORT", "yhin to hai hi")
                }

                if (recyclerview_searchPage == null)
                {
                    Log.d("SHORT","Sorry Bro ab mai Crash krega")
                }else
                    recyclerview_searchPage.adapter = adapter

            }
        })
    }
}


class SearchAddingData(val str :uploadInstitute): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.recycler_view_holder_home
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Log.d("TRUEE","Entered the SearchAdiingClass")
        viewHolder.itemView.name_recyclerItem.text = str.insName
        viewHolder.itemView.address_recyclerItem.text = str.insAddress
    }

}

