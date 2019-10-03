package com.errorfoundteam.nist.coachingdhundho

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_view_holder_home.view.*

class homeFragment : Fragment() {


    var photoUri: Uri? = null

    lateinit var image: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adp = GroupAdapter<ViewHolder>()
        val mainMenu = view?.findViewById(R.id.recycleViewHomeId) as RecyclerView
        mainMenu.layoutManager = LinearLayoutManager(this.context)
        recycleViewHomeId.adapter = adp
        getdata()

    }

    fun getdata() {

        val ref = FirebaseDatabase.getInstance().getReference("/Institutes")
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }


            override fun onDataChange(p0: DataSnapshot) {

                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach {
                    val COACHING = it.getValue(uploadInstitute::class.java)
                    Log.d("Save", it.toString())

                    if (COACHING != null)

                       // adapter.add(Order(COACHING))
                    Log.d("Save", "yhin to hai hi")

                }
                recycleViewHomeId.adapter = adapter

            }


        })

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}


class Order(val data: SaveData) : Item<ViewHolder>() {

    override fun getLayout(): Int {

        return R.layout.recycler_view_holder_home

    }


    override fun bind(viewHolder: ViewHolder, position: Int) {

        Log.d("Save", "yhaan tk pouncha")

        viewHolder.itemView.Viewholder1_textNameid.text = data.coachingname
        viewHolder.itemView.Viewholder1_textpriceid.text = data.price
        viewHolder.itemView.Viewholder1_textaddid.text = data.addresse

        viewHolder.itemView.Viewholder1_textmonthid.text = data.day

        viewHolder.itemView.Viewholder1_textstarid.text = data.rating



        Picasso.get().load(data.link).into(viewHolder.itemView.Viewholder1_imageid)

    }


}








   // private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    //override fun onCreate(savedInstanceState: Bundle?) {
      //  super.onCreate(savedInstanceState)
//
//
//
//                //calling function
//                mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
//
//                // Set up the ViewPager with the sections adapter.
//                viewPager_id.adapter = mSectionsPagerAdapter
//
//
//
//            }
//            //adapter starts
//            inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//                override fun getItem(position: Int): Fragment {
//                    // getItem is called to instantiate the fragment for the given page.
//                    // Return a PlaceholderFragment (defined as a static inner class below).
//                    return PlaceholderFragment.newInstance(position + 1)
//                }
//
//                override fun getCount(): Int {
//                    // Show 3 total pages.
//
//                    return 3
//                }
//
//                override fun getPageTitle(position: Int): CharSequence {
//                    return when (position) {
//                        0 -> "All"
//                        1 -> "New"
//                        else -> {
//                            return "Near you"
//                        }}}
//
//            } //adapter closes
//
//
//            class PlaceholderFragment : Fragment() {
//
//                override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                                          savedInstanceState: Bundle?): View? {
//
//                    var rootView=view
//                    if (arguments?.getInt(ARG_SECTION_NUMBER)== 1 ) {
//                        rootView = inflater.inflate(R.layout.fragment_child__fragment_one, viewPager_id, false)
//                        //rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
//                        return rootView
//
//                    }
//                    else {
//                        if (arguments?.getInt(ARG_SECTION_NUMBER)== 2){
//                            rootView = inflater.inflate(R.layout.fragment_two,viewPager_id, false)
//                            return rootView
//                        }
//
//                    }
//
//
//
//                }
//                companion object {
//                    /**
//                     * The fragment argument representing the section number for this
//                     * fragment.
//                     */
//                    private val ARG_SECTION_NUMBER = "section_number"
//
//                    /**
//                     * Returns a new instance of this fragment for the given section
//                     * number.
//                     */
//                    fun newInstance(sectionNumber: Int): PlaceholderFragment {
//                        val fragment = PlaceholderFragment()
//                        val args = Bundle()
//                        args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//                        fragment.arguments = args
//                        return fragment
//                    }
//                }
//            }
//
////    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    setUpViewPager(viewPager_id)
//
//        Tablayout_id.setupWithViewPager(viewPager_id)
//    }
//    private fun setUpViewPager(viewPager: ViewPager) {
//        mSectionsPagerAdapter= new
//
//    }
//        }
