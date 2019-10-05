package com.example.user.coachingdhundho

import android.os.Bundle
import androidx.fragment.app.Fragment

class homeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        }

    }

//    fun getdata() {
//
//        val ref = FirebaseDatabase.getInstance().getReference("COACHING")
//        ref.addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//
//            override fun onDataChange(p0: DataSnapshot) {
//
//                val adapter = GroupAdapter<ViewHolder>()
//
//                p0.children.forEach {
//
//                    val COACHING = it.getValue(SaveData::class.java)
//
//                    Log.d("Save", it.toString())
//
//
//
//                    if (COACHING != null)
//
//
//                        adapter.add(Order(COACHING))
//
//                    Log.d("Save", "yhin to hai hi")
//
//
//                }
//
//                recycleViewHomeId.adapter = adapter
//
//            }
//
//
//        })
//
//    }
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//}
//
//
//class Order(val data: SaveData) : Item<ViewHolder>() {
//
//    override fun getLayout(): Int {
//
//        return R.layout.recycler_view_holder_home
//
//    }
//
//
//    override fun bind(viewHolder: ViewHolder, position: Int) {
//
//        Log.d("Save", "yhaan tk pouncha")
//
//        viewHolder.itemView.Viewholder1_textNameid.text = data.coachingname
//        viewHolder.itemView.Viewholder1_textpriceid.text = data.price
//        viewHolder.itemView.Viewholder1_textaddid.text = data.addresse
//
//        viewHolder.itemView.Viewholder1_textmonthid.text = data.day
//
//        viewHolder.itemView.Viewholder1_textstarid.text = data.rating
//
//
//
//        Picasso.get().load(data.link).into(viewHolder.itemView.Viewholder1_imageid)
//
//    }
//
//
//}









