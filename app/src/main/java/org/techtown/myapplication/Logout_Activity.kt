package org.techtown.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Logout_Activity : AppCompatActivity() {

    lateinit var out : TextView

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")

        out = findViewById(R.id.out)

        out.setOnClickListener {

            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}