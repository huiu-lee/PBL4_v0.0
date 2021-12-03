package org.techtown.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resource.*
import kotlinx.android.synthetic.main.activity_resource_plant.*

class resourcePlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_plant)

        // 뒤로가기 버튼
        back_normal_plant2.setOnClickListener{
            finish()
        }
    }
}