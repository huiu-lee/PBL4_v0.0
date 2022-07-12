package org.techtown.myapplication

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_buyerlist_second.*
import kotlinx.android.synthetic.main.activity_buyerlist_second.address
import kotlinx.android.synthetic.main.activity_buyerlist_second.zipcode
import kotlinx.android.synthetic.main.activity_trade_process.*
import java.util.regex.Pattern

class BuyerlistActivity_third : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    private lateinit var dbref : DatabaseReference
    private lateinit var dbref2 : DatabaseReference
    private lateinit var buyerArrayList: ArrayList<Buyer>
    //lateinit var databaseReference: DatabaseReference

    lateinit var bottomNav_nxt : Button
    lateinit var howmuchbuy : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyerlist_third)

//        sellerRecyclerView = findViewById(R.id.sellerlist_3rd)
//        sellerRecyclerView.layoutManager = LinearLayoutManager(this)
//        sellerRecyclerView.setHasFixedSize(true)
//
//        sellerArrayList = arrayListOf<Seller>()
//        getSellerData()

        howmuchbuy = findViewById(R.id.howmuchbuy)

        if (intent.hasExtra("howmuchSell")) {
            val howmuchEdit3 = intent.getStringExtra("howmuchSell")
            if (howmuchEdit3 != null) {
                howmuchbuy.text = howmuchEdit3.toString()
            }
        }

        database = FirebaseDatabase.getInstance()

        // 판매처 보여줌
        var y=""

        val x = MySharedPreferences.getUserId(this)

        if(x=="test@gmail.com")
            y="-MwCVkmDQ7lbUpG05BRH"


        var myRef7 = database.getReference("Users").child("users").child(y).child("zipcode")
        var myRef8 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("address")


        myRef7.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value1 = datasnapshot?.value
                zipcode.text=value1.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        myRef8.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value2 = datasnapshot?.value
                address.text=value2.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // 구매처 보여줌
        if (intent.hasExtra("buyer")) {
            val buyer = intent.getStringExtra("buyer")
            if (buyer != null) {
                dbref = FirebaseDatabase.getInstance().getReference("buyer")

                dbref.child(buyer).get().addOnSuccessListener {

                    if (it.exists()) {

                        val Buypowerplant = it.child("Buypowerplant").value
                        val Buyaddress = it.child("Buyaddress").value
                        val point = it.child("point").value
                        Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()

                        val buy_powerplant : TextView = findViewById(R.id.buy_powerplant)
                        val buy_address : TextView = findViewById(R.id.buy_address)
                        val memo_buyer_3rd: TextView = findViewById(R.id.memo_buyer_3rd)
                        val memo_point_3rd: TextView = findViewById(R.id.memo_point_3rd)

                        buy_powerplant.text = Buypowerplant.toString()
                        buy_address.text = Buyaddress.toString()
                        memo_buyer_3rd.text = Buypowerplant.toString()
                        memo_point_3rd.text = point.toString()
                    } else {
                        Toast.makeText(this, "buyer Doesn't Exist", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bottomNav_nxt = findViewById(R.id.okaybtn)
        buyerArrayList = arrayListOf<Buyer>()
        bottomNav_nxt.setOnClickListener {
            //안심번호 팝업창 뜨고 번호가 맞아야 판매량 바뀌기
            showCustomAlert()
        }

    }

//    private fun getSellerData() {
//        dbref = FirebaseDatabase.getInstance().getReference("seller")
//
//        dbref.child("1").get().addOnSuccessListener {
//
//            if (it.exists()) {
//                val Selpowerplant = it.child("Selpowerplant").value
//                val memo_seller_3rd: TextView = findViewById(R.id.memo_seller_3rd)
//
//                memo_seller_3rd.text = Selpowerplant.toString()
//            }
//
//        }
//
//        dbref.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()){
//
//                    for (userSnapshot in snapshot.children) {
//
//                        val seller = userSnapshot.getValue(Seller::class.java)
//                        sellerArrayList.add(seller!!)
//                    }
//
//                    sellerRecyclerView.adapter = SellerAdapter(sellerArrayList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }

    // 안심번호 팝업창 보여주기
    private fun showCustomAlert() {
        var builder = AlertDialog.Builder(this)
        // xml파일로 커스텀 다이얼로그 디자인을 보여줄때
        var v1 = layoutInflater.inflate(R.layout.safe_dialog, null)
        builder.create()

        var safeEdit = v1.findViewById<EditText>(R.id.safeEdit)
        var safeYes = v1.findViewById<TextView>(R.id.safeYes)

        safeYes.setOnClickListener {

            var safeNum = safeEdit.text.toString()

            if (safeNum.isEmpty()) {
                Toast.makeText(this, "Safe Number required", Toast.LENGTH_SHORT).show()
            } else {
                if (isValidSafeNum(safeNum)) {
                    isSafeNumExist(safeNum)
                } else {
                    Toast.makeText(this, "Check your safe number", Toast.LENGTH_SHORT).show()
                }
            }
        }

        builder.setView(v1)
        builder.show()

    }

    // 안심번호 유효성 검사
    private fun isValidSafeNum(safeNum: String): Boolean {
        return Pattern.matches("^[0-9]*\$", safeNum)
    }

    // 입력된 안심번호와 데베에 저장된 안심번호 비교
    private fun isSafeNumExist(safeNum: String) {

        dbref2 = database.getReference("Users").child("users")
        dbref2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var list = java.util.ArrayList<User>()
                var issafenumexist = false
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.safeNum == safeNum) {
                        issafenumexist = true
                    }
                    list.add(value!!)
                }

                if (issafenumexist) {
                    Toast.makeText(this@BuyerlistActivity_third, "Safe number match", Toast.LENGTH_SHORT).show()

                    if (intent.hasExtra("howmuchSell")) {
                        val howmuchEdit = intent.getStringExtra("howmuchSell")
                        if (howmuchEdit != null) {
                            updateData(howmuchEdit)
                            updatePoint(howmuchEdit)

                            startActivity(Intent(applicationContext, mainActivity::class.java))
                        }
                    }
                } else {
                    Toast.makeText(this@BuyerlistActivity_third, "Safe number not match! check your safe number", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun updateData(howmuchEdit: String) {
        // 확인 누르면 DB의 SelEditpower이 입력된 값으로 바뀜
        database = FirebaseDatabase.getInstance()

        if (intent.hasExtra("buyer")) {
            val buyer = intent.getStringExtra("buyer")
            if (buyer != null) {

                // 판매자의 전력량 업데이트
                var inputelec = 0
                var total = 0
                var input = 0

                inputelec=howmuchEdit.toInt()

                var y=""

                val x = MySharedPreferences.getUserId(this@BuyerlistActivity_third)

                if(x=="test@gmail.com")
                    y="-MwCVkmDQ7lbUpG05BRH"

                var myRef9 = database.getReference("Users").child("users").child(y).child("elec")

                myRef9.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(datasnapshot: DataSnapshot) {
                        val value1 = datasnapshot.getValue<Int>()
                        input=value1.toString().toInt()
                        total=input-inputelec
                        myRef9.setValue(total)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })

                // 구매자의 전력량 업데이트
                var inputelec2 = 0
                var total2 = 0
                var input2 = 0

                inputelec2=howmuchEdit.toInt()

                var myRef10 = database.getReference("buyer").child(buyer).child("elec")

                myRef10.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(datasnapshot: DataSnapshot) {
                        val value2 = datasnapshot.getValue<Int>()
                        input2=value2.toString().toInt()
                        total2=input2+inputelec2
                        myRef10.setValue(total2)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })

            }


        }
    }

    private fun updatePoint(howmuchEdit: String) {

        if (intent.hasExtra("buyer")) {
            val buyer = intent.getStringExtra("buyer")
            if (buyer != null) {

                // 판매자의 포인트 업데이트
                var point = 0
                var total3 = 0
                var input3 = 0

                point=howmuchEdit.toInt()

                var y=""

                val x = MySharedPreferences.getUserId(this@BuyerlistActivity_third)

                if(x=="test@gmail.com")
                    y="-MwCVkmDQ7lbUpG05BRH"

                var myRef11 = database.getReference("Users").child("users").child(y).child("point")

                myRef11.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(datasnapshot: DataSnapshot) {
                        val value1 = datasnapshot.getValue<Int>()
                        input3=value1.toString().toInt()
                        total3=input3+point
                        myRef11.setValue(total3)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })

                // 구매자의 포인트 업데이트
                var point2 = 0
                var total4 = 0
                var input4 = 0

                point2=howmuchEdit.toInt()

                var myRef12 = database.getReference("buyer").child(buyer).child("point")

                myRef12.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(datasnapshot: DataSnapshot) {
                        val value2 = datasnapshot.getValue<Int>()
                        input4=value2.toString().toInt()
                        total4=input4-point2
                        myRef12.setValue(total4)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }
                })

            }
        }



    }
}