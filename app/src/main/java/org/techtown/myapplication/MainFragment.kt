//package org.techtown.myapplication
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import androidx.viewpager.widget.ViewPager
//import kotlinx.android.synthetic.main.fragment_main.*
//import org.joda.time.format.DateTimeFormat
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import android.view.LayoutInflater
//import android.view.ViewGroup
//
//class MainFragment : Fragment() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_main)
//
//        //  initialize viewpager head
//        val viewPager_head = findViewById<ViewPager>(R.id.viewPager_head)
//        viewPager_head.adapter = MainFragment@adapter_head
//
//        //  initialize viewpager middle
//        val viewPager_middle = findViewById<ViewPager>(R.id.viewPager_middle)
//        viewPager_middle.adapter = MainFragment@adapter_middle
//
//        // 이미지 양쪽 미리보기
//        val dpValue : Int = 100
//        val d : Float = getResources().getDisplayMetrics().density
//        val margin: Int = dpValue * d.toInt()
//        viewPager_middle.setClipToPadding(false)
//        viewPager_middle.setPadding(margin, 0, margin, 0);
//        viewPager_middle.setPageMargin(margin/2);
//
//        //  indicator head
//        viewPager_head.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(p0_head: Int) {
//            }
//
//            override fun onPageScrolled(p0_head: Int, p1_head: Float, p2_head: Int) {
//            }
//
//            override fun onPageSelected(p0_head: Int) {
//                val indicator0_iv_main = findViewById<ImageView>(R.id.indicator0_iv_main)
//                val indicator1_iv_main = findViewById<ImageView>(R.id.indicator1_iv_main)
//                val indicator2_iv_main = findViewById<ImageView>(R.id.indicator2_iv_main)
//
//                indicator0_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//                indicator1_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//                indicator2_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//
//                when(p0_head){
//                    0 -> {
//                        indicator0_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_white))
//                        main_date.visibility = View.VISIBLE
//                    }
//                    1 -> {
//                        indicator1_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_white))
//                        main_date.visibility = View.GONE
//                    }
//                    2 -> {
//                        indicator2_iv_main.setImageDrawable(getDrawable(R.drawable.shape_circle_white))
//                        main_date.visibility = View.GONE
//                    }
//
//                }
//            }
//        })
//
//        //  indicator middle
//        viewPager_middle.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(p0_middle: Int) {
//            }
//
//            override fun onPageScrolled(p0_middle: Int, p1_middle: Float, p2_middle: Int) {
//            }
//
//            override fun onPageSelected(p0_middle: Int) {
//                val indicator0_iv_middle = findViewById<ImageView>(R.id.indicator0_iv_middle)
//                val indicator1_iv_middle = findViewById<ImageView>(R.id.indicator1_iv_middle)
//                val indicator2_iv_middle = findViewById<ImageView>(R.id.indicator2_iv_middle)
//                val indicator3_iv_middle = findViewById<ImageView>(R.id.indicator3_iv_middle)
//
//                indicator0_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//                indicator1_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//                indicator2_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//                indicator3_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
//
//                when(p0_middle){
//                    0 -> indicator0_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_point))
//                    1 -> indicator1_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_point))
//                    2 -> indicator2_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_point))
//                    3 -> indicator3_iv_middle.setImageDrawable(getDrawable(R.drawable.shape_circle_point))
//                }
//            }
//        })
//
//        // date 표현
//        val timeDate : String
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val date = LocalDateTime.now()
//            val dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd")
//            val nowString = date.format(dtf)
//            timeDate = nowString
//        } else {
//            val date = org.joda.time.LocalDateTime.now()
//            val dtf = DateTimeFormat.forPattern("yyyy.MM.dd")
//            val jodaTime = dtf.parseDateTime(date.toString())
//            val nowString = DateTimeFormat.forPattern("yyyy.MM.dd").print(jodaTime)
//            timeDate = nowString
//        }
//
//        main_date.text = timeDate
//    }
//
//
//
//}