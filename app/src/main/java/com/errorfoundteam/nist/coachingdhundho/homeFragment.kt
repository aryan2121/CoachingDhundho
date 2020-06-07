package com.errorfoundteam.nist.coachingdhundho

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.DocumentsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_view_holder_home.*
import kotlinx.android.synthetic.main.recycler_view_holder_home.view.*
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class homeFragment : Fragment() {

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recycleViewHomeId.setLayoutManager(layoutManager)
        val adpter = GroupAdapter<ViewHolder>()
        recycleViewHomeId.adapter = adpter
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        adpter.add(placeholder())
        getdata(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    companion object {
        const val Coaching_Key = "HOSTEL_KEY"
        const val array_list = "ARRAYS"
    }
    private  fun getdata(context: Context) {

        val array = Array<String>(100){ String()}
        var number = 1
        val intent = Intent(context, openInstitute::class.java)

        Toast.makeText(context,"This is Home Activity",Toast.LENGTH_SHORT).show()
        val ref = FirebaseDatabase.getInstance().getReference("/Institutes/")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context,"Sorry Some Error occured",Toast.LENGTH_SHORT).show()
            }


            override fun onDataChange(p0: DataSnapshot) {

                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    array[number] = it.key.toString()
                    number++

                    val sime = it.childrenCount
                    Log.d("LELELE",sime.toString())

                    val coaching= it.getValue(uploadInstitute::class.java)
                    Log.d("HomeFragment", it.toString())

                    adapter.setOnItemClickListener { item, view ->
                        val coachingItem = item as Order
                        intent.putExtra("SEARCHKEY", "")
                        intent.putExtra("HOMEKEY", "HOMEKEY")
                        intent.putExtra(Coaching_Key, coachingItem.coaching)
                        startActivity(intent)

                    }
                    if (coaching != null)
                        adapter.add(Order(coaching,context))

                    Log.d("Save", "yhin to hai hi")
                }

                intent.putExtra(array_list,array)
                if (recycleViewHomeId == null)
                {
                    Log.d("Open","Sorry Bro ab mai Crash krega")
                }else
                    recycleViewHomeId.adapter = adapter

            }
        })
    }
}



//Settig up data to the fields
class Order(val coaching: uploadInstitute,val context: Context) : Item<ViewHolder>() {

    override fun getLayout(): Int {

        return R.layout.recycler_view_holder_home


    }


    override fun bind(viewHolder: ViewHolder, position: Int) {

        Log.d("Save", "yhaan tk pouncha")

        viewHolder.itemView.relativeLayout_recyclerItem.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_animation_recycleritem))

        //viewHolder.itemView.name_recyclerItem.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_animation_recycleritem))
        viewHolder.itemView.name_recyclerItem.text = coaching.insName
        if (coaching.insAddress.isNotEmpty()) {
            viewHolder.itemView.address_recyclerItem.text = coaching.insAddress
        }else
        {
            val address = "Ranchi , Jharkhand"
            viewHolder.itemView.address_recyclerItem.text = address
        }

        Picasso.get().load(R.drawable.placeholder_img).into(viewHolder.itemView.ImageView_recycleritem)
        if (coaching.insLogo.isNotEmpty()) {
            Picasso.get().load(coaching.insLogo).resize(50,50).placeholder(R.drawable.placeholder_img).into(viewHolder.itemView.ImageView_recycleritem)
        }else if (coaching.insBanner1.isNotEmpty()) {
            Picasso.get().load(coaching.insBanner1).resize(50,50).placeholder(R.drawable.placeholder_img).into(viewHolder.itemView.ImageView_recycleritem)
        }else if (coaching.insImage.isNotEmpty()) {
            Picasso.get().load(coaching.insImage).resize(50,50).placeholder(R.drawable.placeholder_img).into(viewHolder.itemView.ImageView_recycleritem)
        }
    }
}



//Placeholder Layout
class placeholder:Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.layout_placeholder_recycleritems
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

}



