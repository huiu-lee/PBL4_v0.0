package org.techtown.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_buyerlist_second.*

class BuyerlistActivity_second : AppCompatActivity() {

    lateinit var send_spin : Spinner
    lateinit var bottomNav_nxt : BottomNavigationView
    lateinit var whoEdit : EditText

    lateinit var database : FirebaseDatabase
    private lateinit var dbref : DatabaseReference
    private lateinit var sellerRecyclerView: RecyclerView
    private lateinit var sellerArrayList: ArrayList<Seller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyerlist_second)

        send_spin = findViewById(R.id.send_spin)
        bottomNav_nxt = findViewById(R.id.bottomNav_nxt)
        whoEdit = findViewById(R.id.whoEdit)

        // 스피너 설정 string - array[send_group]
        val send_group = resources.getStringArray(R.array.send_group)

        val sendAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, send_group)
        send_spin.adapter = sendAdapter

        if (intent.hasExtra("howmuchSell")) {
            val howmuchEdit = intent.getStringExtra("howmuchSell")
            if (howmuchEdit != null) {
                howmuchbuy.text = howmuchEdit.toString()
            }
        }

        bottomNav_nxt.setOnClickListener {

            val buyer : String = whoEdit.text.toString()
            val howmuchEdit = intent.getStringExtra("howmuchSell")

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
            intent.putExtra("howmuchSell", howmuchEdit)
            startActivity(intent)

        }

        sellerRecyclerView = findViewById(R.id.sellerlist_2nd)
        sellerRecyclerView.layoutManager = LinearLayoutManager(this)
        sellerRecyclerView.setHasFixedSize(true)

        sellerArrayList = arrayListOf<Seller>()
        getSellerData()



    }

    private fun getSellerData() {
        dbref = FirebaseDatabase.getInstance().getReference("seller")

        dbref.child("1").get().addOnSuccessListener {

            if (it.exists()) {
                val Selpowerplant = it.child("Selpowerplant").value
                val memo_seller_2nd: TextView = findViewById(R.id.memo_seller_2nd)

                memo_seller_2nd.text = Selpowerplant.toString()
            }

        }

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children) {

                        val seller = userSnapshot.getValue(Seller::class.java)
                        sellerArrayList.add(seller!!)
                    }

                    sellerRecyclerView.adapter = SellerAdapter(sellerArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // 2번째 액티비티(전력 판매량 입력하는 페이지) override fun onCreate(savedInstanceState: Bundle?) {} 에 입력해야 함
    // 확인버튼 id는 바꿔도 됨
    // howmuchEdit은 EditText의 id, 바꿔도 됨
    //확인버튼 누르면
//    deal_next.setOnClickListener {
//
//        var howmuchEdit = howmuchEdit.text.toString()
//
//        var intent = Intent(this, BuyerlistActivity_second::class.java)
//        // intent로 다른 엑티비티로 변수 넘기기 가능
//        intent.putExtra("howmuchSell", howmuchEdit)
//        startActivity(intent)

        //updateData(howmuchEdit)

    //}
}