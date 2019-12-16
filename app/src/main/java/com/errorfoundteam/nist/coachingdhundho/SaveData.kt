package com.errorfoundteam.nist.coachingdhundho

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.android.parcel.Parcelize

@Parcelize
class FeedbackUpload(val feedback : String): Parcelable {
    constructor():this("")
}

class SaveData (
        val coachingname : String,
        val location : String ,
        val link : String,
        val addresse : String,
        val price: String,
        val day: String,
        val rating : String)
{

    constructor() : this("", "","","","","","")

}



@Parcelize
class SavingUserData(val userName : String, val userEmail : String, val id : String,
                     val InsName : String,
                     val InsAddress : String,
                     val INsRegFee :String) : Parcelable{
    constructor() : this(userName = "",
            userEmail = "",
            id = "",
            InsName = "",
            InsAddress = "",
            INsRegFee = "")
}

@Parcelize
class uploadInstitute(val insName : String ,
                      val ownerName : String ,
                      val city : String ,
                      val state : String ,
                      val pinCode : String ,
                      val insAddress : String ,
                      val mobilenumber : String ,
                      val insType : String ,
                      val fascilities : String ,
                      val insregistrationfees : String,
                      val insLogo : String,
                      val insBanner1 : String,
                      val insBanner2 : String,
                      val insBanner3 : String,
                      val insBanner4 : String,
                      val insBanner5 : String,
                      val insImage : String,
                      val extrasubject : String,
                      val lati : String,
                      val longi : String):Parcelable{
    constructor():this("","","","","","","","","",
            "","","","","","","","",
            "","","")
}


class uploadLKGto10(val math :String,
                    val english : String,
                    val scienceorevs : String,
                    val hindi : String,
                    val sst : String){
    constructor():this("","","","","")
}

class uploadclass11science(val physics : String,
                           val chemistry : String,
                           val math : String,
                           val computer  : String,
                           val biology : String,
                           val english : String){
    constructor():this("","","","","","")
}


class uploadclass12science(val physics : String,
                           val chemistry : String,
                           val math : String,
                           val computer  : String,
                           val biology : String,
                           val english : String){
    constructor():this("","","","","","")
}


class uploadclass11commerce(val accounts : String,
                            val bst : String,
                            val economics : String,
                            val bmt  : String,
                            val etp : String,
                            val computer : String,
                            val english: String){
    constructor():this("","","","","","","")
}

class uploadclass12commerce(val accounts : String,
                            val bst : String,
                            val economics : String,
                            val bmt  : String,
                            val etp : String,
                            val computer : String,
                            val english: String){
    constructor():this("","","","","","","")
}

class uploadcomputercources(val  dca : String,
                            val tally : String,
                            val adca : String,
                            val dtp : String,
                            val c : String,
                            val ccc : String,
                            val cpp : String,
                            val corejava : String,
                            val advjava  : String,
                            val python : String,
                            val android : String,
                            val machinelearning : String,
                            val diplomainFA : String,
                            val vbnet : String,
                            val html  : String,
                            val css : String,
                            val csharp : String,
                            val php : String,
                            val digitalmarketing : String,
                            val webdesigning : String,
                            val vfxandanimation : String,
                            val hardwareandnetworking : String,
                            val graphicsdesign : String,
                            val cadd : String){
    constructor():this("","","","","","","","","","","",
            "","","","","","","","","",
            "","","","")
}


@Parcelize
class uploadcompetitiveexams(val  ssc : String,
                             val railway : String,
                             val banking : String,
                             val sscRailway : String,
                             val nda : String,
                             val nevalacademy : String,
                             val ndaNA : String,
                             val jpsc : String,
                             val upsc  : String,
                             val cds : String,
                             val iitjee : String,
                             val meical : String,
                             val ugcNet : String,
                             val aipmt : String,
                             val polytechnic  : String,
                             val spokenEnglish: String):Parcelable{
    constructor():this("","","","","","","","","","",
            "","","","","","")
}

class uploadgraduationcources(val  BSCphysics : String,
                              val BSCchemistry : String,
                              val BSCmaths : String,
                              val BCOM : String,
                              val BBA : String,
                              val BCA : String,
                              val Btech : String,
                              val BSCit : String,
                              val MCA  : String,
                              val MCOM : String,
                              val MBA : String,
                              val CScwa : String,
                              val CPT : String,
                              val Botony : String,
                              val Zoology  : String){
    constructor():this("","","","","","","","",""
    ,"","","","","","")
}