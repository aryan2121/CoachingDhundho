package com.errorfoundteam.nist.coachingdhundho

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.Placeholder
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_open_institute.*
import kotlinx.android.synthetic.main.activity_open_institute.view.*
import kotlinx.android.synthetic.main.coursedetails_container.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.coroutines.coroutineContext

class openInstitute : AppCompatActivity() {
    lateinit var coaching : uploadInstitute
    companion object{
        const val PAYMENT = "PAYMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_institute)

        val layoutManager = LinearLayoutManager(this)
        openInstitiuterecycler.setLayoutManager(layoutManager)
        val adpter = GroupAdapter<ViewHolder>()
        openInstitiuterecycler.adapter = adpter


        val homekey = intent.getStringExtra("HOMEKEY")
        val searchkey = intent.getStringExtra("SEARCHKEY")




        if (homekey.isNotEmpty()) {
            coaching = intent.getParcelableExtra<uploadInstitute>("HOSTEL_KEY")
        }else
             coaching = intent.getParcelableExtra<uploadInstitute>("SEARCHDATA")
        val courseCompetitive = intent.getParcelableExtra<uploadcompetitiveexams>("COMPTETIVE")

           // Log.d("OpenInstitute", courseCompetitive.jpsc)
//        Log.d("LOL", courseCompetitive.upsc)
//        Log.d("OpenInstitute", courseCompetitive.iitjee)
//        Log.d("OpenInstitute", courseCompetitive.railway)
//        Log.d("OpenInstitute", courseCompetitive.ssc)

        openInst_INSTname_textViewId.text = coaching.insName
        if(coaching.fascilities.isNotEmpty()) {
            openInst_Details_textViewId.text = coaching.fascilities
        }else
            openInst_Details_textViewId.text = "This Institute Provide Experienced Faculties\n Best Learning Enviornment\n"
        openInst_Location_textViewId.text = coaching.insAddress
        texteView_price_openIns.text = "Reg fee : "+coaching.insregistrationfees

        if (coaching.insImage.isNotEmpty()){
            Picasso.get().load(coaching.insImage).into(categoryImgId)
        }else
            if (coaching.insBanner1.isNotEmpty()){
                Picasso.get().load(coaching.insBanner1).into(categoryImgId)
            }else
                if (coaching.insImage.isNotEmpty()){
            Picasso.get().load(coaching.insImage).into(categoryImgId)
        }


        //Enroll BUtton Onclick performance
        openInst_Enroll_ButtonId.setOnClickListener {
            if (coaching.insregistrationfees.isNotEmpty()) {
                val i = Intent(this,Enroll_payment_Activity::class.java)
                i.putExtra(PAYMENT,coaching)
                startActivity(i)
            }
        }

        //calling an institute by the call button
        openInst_Call_ButtonId.setOnClickListener{
            val number = coaching.mobilenumber
            callButton()

            val makeCall = Intent(Intent.ACTION_DIAL)
            makeCall.setData(Uri.parse("tel:$number"))
            startActivity(makeCall)

        }

        //GET DIRECTION ON MAP LISTNER
        getDirectionButtonId.setOnClickListener {

            if (coaching.lati.isNotEmpty()) {
                val gmmIntentUri = Uri.parse("google.navigation:q=${coaching.lati},${coaching.longi}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
            else
                Toast.makeText(this,"Sorry Root not available",Toast.LENGTH_SHORT).show()
        }
        getCoursedata(coaching.insName)



    }

    //uploading user for notification
    private fun callButton(){
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val chillld = acct!!.displayName + acct.id
        val inst = coaching.insName
        var fee = "Not specified"
        if (coaching.insregistrationfees.isNotEmpty()) {
            fee = coaching.insregistrationfees
        }

        val call  = FirebaseDatabase.getInstance().getReference("/Notification/$chillld")
        val userDatatoFirebase = SavingUserData(
                acct.displayName.toString(),
                acct.email.toString(),
                acct.id.toString(),
                coaching.insName,
                coaching.insAddress,
                fee
        )
        call.child(inst).setValue(userDatatoFirebase).addOnCompleteListener {
            Log.d("CallButton Clicked","User Uploaded Becose Call button clicked")
        }
    }

    //getting course details and all
    private fun getCoursedata(name :String){

        val adpter = GroupAdapter<ViewHolder>()

        val reff = FirebaseDatabase.getInstance().getReference().child("/Courses/$name")

        val ruff = FirebaseDatabase.getInstance().getReference("/Courses/").child("$name")
        ruff.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val class12commer = it.getValue(uploadclass12commerce::class.java)
                    val class12science = it.getValue(uploadclass12science::class.java)
                    if (class12commer != null){
                        if (class12commer.accounts.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.accounts))
                        }
                        if (class12commer.bmt.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.bmt))
                        }
                        if (class12commer.computer.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.computer))
                        }
                        if (class12commer.bst.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.bst))
                        }
                        if (class12commer.economics.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.economics))
                        }
                        if (class12commer.english.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.economics))
                        }
                        if (class12commer.etp.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12commer.etp))
                        }
                    }

                    if (class12science != null){
                        if (class12science.biology.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.biology))
                        }
                        if (class12science.chemistry.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.chemistry))
                        }
                        if (class12science.computer.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.computer))
                        }
                        if (class12science.english.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.english))
                        }
                        if (class12science.math.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.math))
                        }
                        if (class12science.physics.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class12science.physics))
                        }
                    }
                }
            }

        })
        reff.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.forEach{
                    val class11commer = it.getValue(uploadclass11commerce::class.java)
                    val competitivecou = it.getValue(uploadcompetitiveexams::class.java)
                    val computerCourses = it.getValue(uploadcomputercources::class.java)
                    val graduationCourse = it.getValue(uploadgraduationcources::class.java)
                    val  lkgtoTEn = it.getValue(uploadLKGto10::class.java)
                    val class11science = it.getValue(uploadclass11science::class.java)

                    if (class11science != null){
                        if (class11science.biology.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.biology))
                        }
                        if (class11science.chemistry.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.chemistry))
                        }
                        if (class11science.computer.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.computer))
                        }
                        if (class11science.math.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.math))
                        }
                        if (class11science.english.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.english))
                        }
                        if (class11science.physics.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11science.physics))
                        }
                    }
                    if (lkgtoTEn != null){
                        if (lkgtoTEn.english.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(lkgtoTEn.english))
                        }
                        if (lkgtoTEn.hindi.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(lkgtoTEn.hindi))
                        }
                        if (lkgtoTEn.math.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(lkgtoTEn.math))
                        }
                        if (lkgtoTEn.scienceorevs.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(lkgtoTEn.scienceorevs))
                        }
                        if (lkgtoTEn.sst.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(lkgtoTEn.sst))
                        }
                    }
                    if (graduationCourse != null){
                        if (graduationCourse.BBA.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BBA))
                        }
                        if (graduationCourse.BCA.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BCA))
                        }
                        if (graduationCourse.BCOM.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BCOM))
                        }
                        if (graduationCourse.BSCchemistry.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BSCchemistry))
                        }
                        if (graduationCourse.BSCit.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BSCit))
                        }
                        if (graduationCourse.BSCmaths.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BSCmaths))
                        }
                        if (graduationCourse.BSCphysics.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.BSCphysics))
                        }
                        if (graduationCourse.Botony.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.Botony))
                        }
                        if (graduationCourse.Btech.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.Btech))
                        }
                        if (graduationCourse.CPT.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.CPT))
                        }
                        if (graduationCourse.CScwa.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.CScwa))
                        }
                        if (graduationCourse.MBA.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.MBA))
                        }
                        if (graduationCourse.MCA.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.MCA))
                        }
                        if (graduationCourse.MCOM.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.MCOM))
                        }
                        if (graduationCourse.Zoology.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(graduationCourse.Zoology))
                        }
                    }
                    if (class11commer != null) {
                        if (class11commer.accounts.isNotEmpty())
                        {
                            adpter.add(CoursePlaceHolder(class11commer.accounts))
                        }
                        if(class11commer.bmt.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.bmt))
                        }
                        if (class11commer.bst.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.bst))
                        }
                        if (class11commer.computer.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.computer))
                        }
                        if (class11commer.economics.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.economics))
                        }
                        if (class11commer.english.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.english))
                        }
                        if (class11commer.etp.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(class11commer.etp))
                        }
                    }
                    if (competitivecou != null){
                        if (competitivecou.jpsc.isNotEmpty()){
                            Log.d("PIKA",competitivecou.jpsc)
                            adpter.add(CoursePlaceHolder(competitivecou.jpsc))
                        }
                        if (competitivecou.aipmt.isNotEmpty()){
                            Log.d("PIKA",competitivecou.aipmt)
                            adpter.add(CoursePlaceHolder(competitivecou.aipmt))
                        }
                        if (competitivecou.banking.isNotEmpty()){
                            Log.d("PIKA",competitivecou.banking)
                            adpter.add(CoursePlaceHolder(competitivecou.banking))
                        }
                        if (competitivecou.cds.isNotEmpty()){
                            Log.d("PIKA",competitivecou.cds)
                            adpter.add(CoursePlaceHolder(competitivecou.cds))
                        }
                        if (competitivecou.iitjee.isNotEmpty()){
                            Log.d("PIKA",competitivecou.iitjee)
                            adpter.add(CoursePlaceHolder(competitivecou.iitjee))
                        }
                        if (competitivecou.meical.isNotEmpty()){
                            Log.d("PIKA",competitivecou.meical)
                            adpter.add(CoursePlaceHolder(competitivecou.meical))
                        }
                        if (competitivecou.nda.isNotEmpty()){
                            Log.d("PIKA",competitivecou.nda)
                            adpter.add(CoursePlaceHolder(competitivecou.nda))
                        }
                        if (competitivecou.ndaNA.isNotEmpty()){
                            Log.d("PIKA",competitivecou.ndaNA)
                            adpter.add(CoursePlaceHolder(competitivecou.ndaNA))
                        }
                        if (competitivecou.nevalacademy.isNotEmpty()){
                            Log.d("PIKA",competitivecou.nevalacademy)
                            adpter.add(CoursePlaceHolder(competitivecou.nevalacademy))
                        }
                        if (competitivecou.polytechnic.isNotEmpty()){
                            Log.d("PIKA",competitivecou.polytechnic)
                            adpter.add(CoursePlaceHolder(competitivecou.polytechnic))
                        }
                        if (competitivecou.railway.isNotEmpty()){
                            Log.d("PIKA",competitivecou.railway)
                            adpter.add(CoursePlaceHolder(competitivecou.railway))
                        }
                        if (competitivecou.spokenEnglish.isNotEmpty()){
                            Log.d("PIKA",competitivecou.spokenEnglish)
                            adpter.add(CoursePlaceHolder(competitivecou.spokenEnglish))
                        }
                        if (competitivecou.ssc.isNotEmpty()){
                            Log.d("PIKA",competitivecou.ssc)
                            adpter.add(CoursePlaceHolder(competitivecou.ssc))
                        }
                        if (competitivecou.sscRailway.isNotEmpty()){
                            Log.d("PIKA",competitivecou.sscRailway)
                            adpter.add(CoursePlaceHolder(competitivecou.sscRailway))
                        }
                        if (competitivecou.ugcNet.isNotEmpty()){
                            Log.d("PIKA",competitivecou.ugcNet)
                            adpter.add(CoursePlaceHolder(competitivecou.ugcNet))
                        }
                        if (competitivecou.upsc.isNotEmpty()){
                            Log.d("PIKA",competitivecou.upsc)
                            adpter.add(CoursePlaceHolder(competitivecou.upsc))
                        }
                    }
                    if (computerCourses != null){
                        if (computerCourses.adca.isNotEmpty()){
                            adpter.add(CoursePlaceHolder(computerCourses.adca))
                        }
                        if (computerCourses.advjava.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.advjava)))
                        }
                        if (computerCourses.android.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.android)))
                        }
                        if (computerCourses.c.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.c)))
                        }
                        if (computerCourses.cadd.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.cadd)))
                        }
                        if (computerCourses.ccc.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.ccc)))
                        }
                        if (computerCourses.corejava.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.corejava)))
                        }
                        if (computerCourses.cpp.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.cpp)))
                        }
                        if (computerCourses.csharp.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.csharp)))
                        }
                        if (computerCourses.css.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.css)))
                        }
                        if (computerCourses.dca.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.dca)))
                        }
                        if (computerCourses.digitalmarketing.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.digitalmarketing)))
                        }
                        if (computerCourses.diplomainFA.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.diplomainFA)))
                        }
                        if (computerCourses.dtp.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.dtp)))
                        }
                        if (computerCourses.graphicsdesign.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.graphicsdesign)))
                        }
                        if (computerCourses.hardwareandnetworking.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.hardwareandnetworking)))
                        }
                        if (computerCourses.html.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.html)))
                        }
                        if (computerCourses.machinelearning.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.machinelearning)))
                        }
                        if (computerCourses.php.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.php)))
                        }
                        if (computerCourses.python.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.python)))
                        }
                        if (computerCourses.tally.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.tally)))
                        }
                        if (computerCourses.vbnet.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.vbnet)))
                        }
                        if (computerCourses.vfxandanimation.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.vfxandanimation)))
                        }
                        if (computerCourses.webdesigning.isNotEmpty()){
                            adpter.add((CoursePlaceHolder(computerCourses.webdesigning)))
                        }
                    }
                }
                openInstitiuterecycler.adapter = adpter
            }
        })
    }
}


//for adding COurse list in recycler view
class CoursePlaceHolder(val name : String): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.coursedetails_container
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView_course_container.text = name.capitalize()
    }

}


