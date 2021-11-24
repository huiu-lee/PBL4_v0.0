package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView


class mainBottomnavActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // default 화면
        setContentView(R.layout.activity_main_bottomnav)

        // 툴바 사용
        setSupportActionBar(findViewById(R.id.toolBar))

        supportActionBar!!.setDisplayShowTitleEnabled(true) // 툴바에 SDT 제목 보이기
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 왼쪽 홈버튼
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_hambuger) // 왼쪽 홈버튼 모양 햄버거로 변경
        
        // 하단바 사용
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu) // 작성한 아이콘
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.action_sidemenu -> {
                // 사이드바 열 때 동작

            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, mainFragment())
                transaction.commit()
                return true
            }
            R.id.action_consumption -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, consumptionFragment())
                transaction.commit()
                return true
            }
            R.id.action_trade -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, tradeFragment())
                transaction.commit()
                return true
            }

            R.id.action_distribution -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, distributionFragment())
                transaction.commit()
                return true
            }

            R.id.action_resource_management -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, resourceFragment())
                transaction.commit()
                return true
            }

        }
        return false
    }



}