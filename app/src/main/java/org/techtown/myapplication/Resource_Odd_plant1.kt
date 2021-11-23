package org.techtown.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resource.*
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.*
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.dealBtn
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.homeBtn
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.resourceBtn
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.shareBtn
import kotlinx.android.synthetic.main.activity_resource_odd_plant1.useBtn

class Resource_Odd_plant1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_odd_plant1)
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

        // 뒤로가기 버튼
        back_odd_plant1.setOnClickListener{
            var intent = Intent(this, ResourceActivity::class.java)
            startActivity(intent)
        }
    }
}