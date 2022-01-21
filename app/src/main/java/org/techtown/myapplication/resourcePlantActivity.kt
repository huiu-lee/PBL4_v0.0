package org.techtown.myapplication

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_resource.*
import kotlinx.android.synthetic.main.activity_resource_plant.*

class resourcePlantActivity : AppCompatActivity() {

    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_plant)

        var plant_name = findViewById<TextView>(R.id.PlantName)
        var plant_measure = findViewById<TextView>(R.id.plant_measure)

        var name = intent.getStringExtra("name")

        plant_name.text = name
        var x = "0"

        if(name == "가구1"){
            x = "1"
        } else if (name == "가구2"){
            x = "2"
        } else {
            x = "3"
        }

        var myRef1 = database.getReference("user").child(x).child("measure")

        myRef1.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                plant_measure.text = value.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })


        // 뒤로가기 버튼
        back_normal_plant2.setOnClickListener{
            finish()
        }
    }
}