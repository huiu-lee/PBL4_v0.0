package org.techtown.myapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class consumptionDetailActivity : AppCompatActivity() {
    val database = Firebase.database

    lateinit var back : ImageView
    lateinit var report : ImageView
    lateinit var d_name : TextView

    var name : String = ""

    lateinit var yy_day : String
    lateinit var y_day : String
    lateinit var t_day : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumption_detail)

        back = findViewById<ImageView>(R.id.back)
        report = findViewById<ImageView>(R.id.reportBtn)
        d_name = findViewById(R.id.d_name)

        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPager2.adapter=adapter

        name = intent.getStringExtra("name").toString()

        d_name.text = name


        //탭 레이아웃 이름 설정
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="일별"
                }
                1->{
                    tab.text="월별"
                }
            }
        }.attach()

        report.setOnClickListener {
            var name = d_name.text

            var intent = Intent(this, consumptionReportActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }

        //뒤로가기 버튼
        back.setOnClickListener {
            finish()
        }

    }
}