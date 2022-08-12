package org.techtown.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_buyerlist_second.*
import kotlinx.android.synthetic.main.activity_buyerlist_second.address
import kotlinx.android.synthetic.main.activity_buyerlist_second.zipcode
import kotlinx.android.synthetic.main.activity_trade_process.*

class BuyerlistActivity_second : AppCompatActivity() {

    lateinit var send_spin: Spinner
    lateinit var bottomNav_nxt: Button
    lateinit var whoEdit: EditText

    lateinit var database: FirebaseDatabase
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyerlist_second)

        send_spin = findViewById(R.id.send_spin)
        bottomNav_nxt = findViewById(R.id.okaybtn)
        whoEdit = findViewById(R.id.whoEdit)

        // 스피너 설정 string - array[send_group]
        val send_group = resources.getStringArray(R.array.send_group)

        val sendAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, send_group)
        send_spin.adapter = sendAdapter

        if (intent.hasExtra("howmuchSell")) {
            val howmuchEdit = intent.getStringExtra("howmuchSell")
            if (howmuchEdit != null) {
                howmuchbuy.text = howmuchEdit
            }
        }

        bottomNav_nxt.setOnClickListener {

            val buyer: String = whoEdit.text.toString()
            val howmuchEdit2 = intent.getStringExtra("howmuchSell")

            var intent = Intent(this, BuyerlistActivity_third::class.java)

            // intent로 다른 엑티비티로 변수 넘기기 가능
            // 받을 땐
            //        if (intent.hasExtra("buyer")) {
            //            val buyer = intent.getStringExtra("buyer")
            //            if (buyer != null) {
            //                getBuyerData(buyer)
            //            }
            //        }
            intent.putExtra("buyer", buyer)
            intent.putExtra("howmuchSell", howmuchEdit2)
            startActivity(intent)

        }

        database = FirebaseDatabase.getInstance()

        // 판매처 보여줌
        var y = ""

        val x = MySharedPreferences.getUserId(this)

        if (x == "test@gmail.com")
            y = "-MwCVkmDQ7lbUpG05BRH"


        var myRef5 = database.getReference("Users").child("users").child(y).child("zipcode")
        var myRef6 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH")
            .child("address")


        myRef5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value1 = datasnapshot?.value
                zipcode.text = value1.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        myRef6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value2 = datasnapshot?.value
                address.text = value2.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

    }
}