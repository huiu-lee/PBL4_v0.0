package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_trade_process.*

class tradeProcessActivity : AppCompatActivity() {

    var inputelec = 0
    var total = 0
    var input = 0

    var run=false

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_process)


        /*
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.password == x) {
                        point.text = value.point.toString()
                        name.text = value.nickname
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        */

        okaybtn.setOnClickListener{
//            if (run == false) {
//                run = true
//                val thread = Elect()
//                thread.start()
//            }

            inputelec=sellelec.text.toString().toInt()

            var intent = Intent(this, BuyerlistActivity_second::class.java)
            // intent로 다른 엑티비티로 변수 넘기기 가능
            intent.putExtra("howmuchSell", inputelec)
            startActivity(intent)


        }

        database = FirebaseDatabase.getInstance()



        var y=""

        val x = MySharedPreferences.getUserId(this)

        if(x=="test@gmail.com") {
            y = "-MwCVkmDQ7lbUpG05BRH"
        }


        var myRef1 = database.getReference("Users").child("users").child(y).child("zipcode")
        var myRef2 = database.getReference("Users").child("users").child("MwCVkmDQ7lbUpG05BRH").child("address")
        var myRef3 = database.getReference("Users").child("users").child(y).child("point")


        myRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value1 = datasnapshot?.value
                zipcode.text=value1.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value2 = datasnapshot?.value
                address.text=value2.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        myRef3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value3 = datasnapshot?.value
                xx.text=value3.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })



    }

    inner class Elect : Thread() {
        override fun run() {

            inputelec=sellelec.text.toString().toInt()

            database = FirebaseDatabase.getInstance()

            var y=""

            val x = MySharedPreferences.getUserId(this@tradeProcessActivity)

            if(x=="test@gmail.com")
                y="-MwCVkmDQ7lbUpG05BRH"

            var myRef4 = database.getReference("Users").child("users").child(y).child("elec")

            myRef4.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(datasnapshot: DataSnapshot) {
                    val value1 = datasnapshot.getValue<Int>()
                    input=value1.toString().toInt()
                    total=input-inputelec
                    myRef4.setValue(total)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
        }
    }
}



