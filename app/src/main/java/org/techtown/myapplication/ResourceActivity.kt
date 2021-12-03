package org.techtown.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resource.*

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        powerPlant2.setOnClickListener{
            var intent = Intent(this, resourcePlantActivity::class.java)
            startActivity(intent)
        }
    }
}