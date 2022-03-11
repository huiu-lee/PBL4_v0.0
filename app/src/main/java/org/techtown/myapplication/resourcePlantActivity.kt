package org.techtown.myapplication

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
//import kotlinx.android.synthetic.main.activity_resource.*
import kotlinx.android.synthetic.main.activity_resource_plant.*
import kotlinx.android.synthetic.main.fragment_mypage.*

class resourcePlantActivity : AppCompatActivity() {


    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_plant)

        var plant_name = findViewById<TextView>(R.id.PlantName)
        var plant_measure = findViewById<TextView>(R.id.plant_measure)

        var name = intent.getStringExtra("name")

        plant_name.text = name

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.nickname == name) {

                        var myRef1 = database.getReference("Users").child("users").child(value.id).child("produce")
                        myRef1.addValueEventListener(object: ValueEventListener {
                            override fun onDataChange(datasnapshot: DataSnapshot) {
                                val value = datasnapshot?.value
                                plant_measure.text = value.toString()
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                            }
                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        // 뒤로가기 버튼
        back_normal_plant2.setOnClickListener{
            finish()
        }
    }
}