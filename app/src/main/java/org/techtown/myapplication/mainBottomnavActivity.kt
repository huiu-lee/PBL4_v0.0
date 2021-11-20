package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView


class mainBottomnavActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, homeFragment())
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // default 화면
        setContentView(R.layout.activity_main_bottomnav)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

}