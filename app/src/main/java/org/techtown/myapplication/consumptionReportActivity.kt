package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.ContentValues
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class consumptionReportActivity : AppCompatActivity() {
    val database = Firebase.database

    lateinit var back2 : ImageView

    lateinit var week_report : TextView
    lateinit var month_report : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumption_report)

        back2 = findViewById<ImageView>(R.id.back2)
        week_report = findViewById(R.id.week_report)
        month_report = findViewById(R.id.month_report)

        var name = intent.getStringExtra("name")

        //val db = Firebase.firestore
        var x = ""

        if(name == "가구1"){
            x = "user1"
        } else if (name == "가구2"){
            x = "user2"
        } else {
            x = "user3"
        }

        var myRef1 = database.getReference(x).child("monthreport")

        myRef1.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue<String>()
                month_report.text = value
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        var myRef2 = database.getReference(x).child("dailyreport")

        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue<String>()
                week_report.text = value
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        //특정 데이터 값 갖고 오기!
//        db.collection("users").whereEqualTo("name", name).get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    var x = document["weekreport"] as String
//                    var y = document["monthreport"] as String
//                    week_report.text = x
//                    month_report.text = y
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
//            }

        //뒤로가기 버튼
        back2.setOnClickListener {
            finish()
        }
    }
}