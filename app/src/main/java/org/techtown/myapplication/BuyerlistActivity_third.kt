package org.techtown.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_buyerlist_second.*
import kotlinx.android.synthetic.main.activity_buyerlist_third.*
import kotlinx.android.synthetic.main.activity_trade_process.*
import org.techtown.myapplication.databinding.ActivityBuyerlistThirdBinding
import java.util.regex.Pattern

class BuyerlistActivity_third : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    private lateinit var dbref : DatabaseReference
    private lateinit var dbref2 : DatabaseReference
    private lateinit var sellerRecyclerView: RecyclerView
    private lateinit var sellerArrayList: ArrayList<Seller>
    private lateinit var buyerArrayList: ArrayList<Buyer>
    lateinit var databaseReference: DatabaseReference

    lateinit var bottomNav_nxt : BottomNavigationView
    lateinit var howmuchbuy : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyerlist_third)

        bottomNav_nxt.setOnClickListener{

            var intent = Intent(this, mainActivity::class.java)
            startActivity(intent)
        }



        databaseReference = database.getReference("Users").child("users")

        sellerRecyclerView = findViewById(R.id.sellerlist_3rd)
        sellerRecyclerView.layoutManager = LinearLayoutManager(this)
        sellerRecyclerView.setHasFixedSize(true)

        sellerArrayList = arrayListOf<Seller>()
        getSellerData()

        if (intent.hasExtra("howmuchSell")) {
            val howmuchEdit = intent.getStringExtra("howmuchSell")
            if (howmuchEdit != null) {
                howmuchbuy.text = howmuchEdit.toString()
            }
        }

        if (intent.hasExtra("buyer")) {
            val buyer = intent.getStringExtra("buyer")
            if (buyer != null) {
                getBuyerData(buyer)
            }
        }

        bottomNav_nxt = findViewById(R.id.bottomNav_nxt)
        buyerArrayList = arrayListOf<Buyer>()
        bottomNav_nxt.setOnClickListener {
            //안심번호 팝업창 뜨고 번호가 맞아야 판매량 바뀌기
            showCustomAlert()
        }

    }

    private fun getBuyerData(buyer: String) {
        dbref = FirebaseDatabase.getInstance().getReference("buyer")

        dbref.child(buyer).get().addOnSuccessListener {

            if (it.exists()) {

                val Buypowerplant = it.child("Buypowerplant").value
                val Buyaddress = it.child("Buyaddress").value
                val Buylocation = it.child("Buylocation").value
                Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()



                val buy_powerplant : TextView = findViewById(R.id.buy_powerplant)
                val buy_address : TextView = findViewById(R.id.buy_address)
                val buy_location : TextView = findViewById(R.id.buy_location)
                val memo_buyer_3rd: TextView = findViewById(R.id.memo_buyer_3rd)

                buy_powerplant.text = Buypowerplant.toString()
                buy_address.text = Buyaddress.toString()
                buy_location.text = Buylocation.toString()
                memo_buyer_3rd.text = Buypowerplant.toString()
                // 받는분 메모 텍스트엔 - 변수명.text = Buypowerplant.toString()
            } else {
                Toast.makeText(this, "buyer Doesn't Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSellerData() {
        dbref = FirebaseDatabase.getInstance().getReference("seller")

        dbref.child("1").get().addOnSuccessListener {

            if (it.exists()) {
                val Selpowerplant = it.child("Selpowerplant").value
                val memo_seller_3rd: TextView = findViewById(R.id.memo_seller_3rd)

                memo_seller_3rd.text = Selpowerplant.toString()
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

    // 안심번호 팝업창 보여주기
    private fun showCustomAlert() {
        var builder = AlertDialog.Builder(this)
        // xml파일로 커스텀 다이얼로그 디자인을 보여줄때
        var v1 = layoutInflater.inflate(R.layout.safe_dialog, null)
        builder.create()

        var safeEdit = v1.findViewById<EditText>(R.id.safeEdit)
        var safeYes = v1.findViewById<TextView>(R.id.safeYes)

        safeYes.setOnClickListener {

            var safeNum = safeEdit.text.toString().trim()

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

        databaseReference.addValueEventListener(object : ValueEventListener {
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
                    startActivity(Intent(applicationContext, mainActivity::class.java))

                    if (intent.hasExtra("howmuchSell")) {
                        val howmuchEdit = intent.getStringExtra("howmuchSell")
                        if (howmuchEdit != null) {
                            updateData(howmuchEdit)
                            updatePoint(howmuchEdit)
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
            dbref2 = database.getReference("buyer")
            if (buyer != null) {

                val howmuch_buy = mapOf<String, String>(
                    "Buyhowmuch" to howmuchEdit
                )

                dbref2.child(buyer).updateChildren(howmuch_buy).addOnSuccessListener {
                    //binding.howmuchEdit.text.clear()
                }.addOnFailureListener {

                }
            }
        }

//        val afterSel = database.getReference("seller").child("1").child("Selhowhave")
//        val afterSum = afterSel.toString() - howmuchEdit2.toString()
//        val howmuch_sell = mapOf<String, String>(
//            "Selhowhave" to afterSum
//        )
//
//        dbref.child("1").updateChildren(howmuch_sell).addOnSuccessListener {
//            howmuchEdit2.text.clear()
//        }.addOnFailureListener {
//
//        }
    }

    private fun updatePoint(howmuchEdit: String) {

        dbref = database.getReference("seller")

        //특정 문자열 변경 W->point
        val str_point = howmuchEdit.replace("W","point")

        val insert_point = mapOf<String, String>(
            "point" to str_point
        )

        dbref.child("1").updateChildren(insert_point).addOnSuccessListener {

        }.addOnFailureListener {

        }

    }
}