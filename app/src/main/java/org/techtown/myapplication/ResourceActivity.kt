package org.techtown.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resource.*

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)
        // 홈버튼을 click으로 유지
        resourceBtn.setImageResource(R.drawable.ic_resource_bottombar_click)

        // 메뉴 페이지로 넘어감, 홈버튼 클릭시 click 이미지로 변경
        homeBtn.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            homeBtn.setImageResource(R.drawable.ic_home_bottombar_click)
        }

        useBtn.setOnClickListener{
//            var intent = Intent(this, ResourceActivity::class.java)
//            startActivity(intent)
            useBtn.setImageResource(R.drawable.ic_consumption_bottombar_click)
        }

        dealBtn.setOnClickListener{
//            var intent = Intent(this, ResourceActivity::class.java)
//            startActivity(intent)
            dealBtn.setImageResource(R.drawable.ic_tradel_bottombar_click)
        }

        shareBtn.setOnClickListener{
//            var intent = Intent(this, ResourceActivity::class.java)
//            startActivity(intent)
            shareBtn.setImageResource(R.drawable.ic_distribution_bottombar_click)
        }

        resourceBtn.setOnClickListener{
            var intent = Intent(this, ResourceActivity::class.java)
            startActivity(intent)
            resourceBtn.setImageResource(R.drawable.ic_resource_bottombar_click)
        }

        // 발전소를 누르면 해당 발전소의 상태를 보여줌
        powerPlant1.setOnClickListener{
            var intent = Intent(this, Resource_Odd_plant1::class.java)
            startActivity(intent)
        }

        powerPlant2.setOnClickListener{
            var intent = Intent(this, Resource_Normal_plant2::class.java)
            startActivity(intent)
        }

        powerPlant3.setOnClickListener{
            var intent = Intent(this, Resource_Normal_plant3::class.java)
            startActivity(intent)
        }

        powerPlant4.setOnClickListener{
            var intent = Intent(this, Resource_Normal_plant4::class.java)
            startActivity(intent)
        }

        powerPlant5.setOnClickListener{
            var intent = Intent(this, Resource_Normal_plant5::class.java)
            startActivity(intent)
        }
    }
}