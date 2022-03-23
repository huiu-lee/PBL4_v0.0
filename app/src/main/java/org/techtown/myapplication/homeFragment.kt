package org.techtown.myapplication

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_home.*
import org.joda.time.format.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable


class homeFragment : Fragment() {

    // 동작 코드
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home, container, false)

        // fragment_main의 view에서 요소찾기, findViewById
        // 1. head viewpager 어댑터
        val viewPager_head = view.findViewById<ViewPager>(R.id.viewPager_head)
        // 슬라이드를 위한 어댑터 설정
        // 프래그먼트 위에 그린 프래그먼트(childFragment)를 교체해야 하므로
        val pagerAdapter_head = ViewPagerAdapter_head(childFragmentManager)
        viewPager_head.adapter = pagerAdapter_head

        // 2. middle viewpager 어댑터
        val viewPager_middle = view.findViewById<ViewPager>(R.id.viewPager_middle)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_middle = ViewPagerAdapter_middle(childFragmentManager)
        viewPager_middle.adapter = pagerAdapter_middle


        // 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue: Int = 100
        val d: Float = getResources().getDisplayMetrics().density
        val margin: Int = dpValue * d.toInt()
        viewPager_middle.setClipToPadding(false)
        viewPager_middle.setPadding(margin, 0, margin, 0);
        viewPager_middle.setPageMargin(margin / 2);

        viewPager_head.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0_head: Int) {
            }

            override fun onPageScrolled(p0_head: Int, p1_head: Float, p2_head: Int) {
            }

            override fun onPageSelected(p0_head: Int) {
                val indicator0_iv_main = view.findViewById<ImageView>(R.id.indicator0_iv_main)
                val indicator1_iv_main = view.findViewById<ImageView>(R.id.indicator1_iv_main)
                val indicator2_iv_main = view.findViewById<ImageView>(R.id.indicator2_iv_main)

                indicator0_iv_main.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
                indicator1_iv_main.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
                indicator2_iv_main.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
            }
        })

        viewPager_middle.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0_middle: Int) {
            }

            override fun onPageScrolled(p0_middle: Int, p1_middle: Float, p2_middle: Int) {
            }

            override fun onPageSelected(p0_middle: Int) {
                val indicator0_iv_middle = view.findViewById<ImageView>(R.id.indicator0_iv_middle)
                val indicator1_iv_middle = view.findViewById<ImageView>(R.id.indicator1_iv_middle)
                val indicator2_iv_middle = view.findViewById<ImageView>(R.id.indicator2_iv_middle)
                val indicator3_iv_middle = view.findViewById<ImageView>(R.id.indicator3_iv_middle)

                indicator0_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
                indicator1_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
                indicator2_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))
                indicator3_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_gray))

                when (p0_middle) {
                    0 -> indicator0_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_point))
                    1 -> indicator1_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_point))
                    2 -> indicator2_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_point))
                    3 -> indicator3_iv_middle.setImageDrawable(getDrawable(view.context, R.drawable.shape_circle_point))
                }
            }
        })

        return view
    }


}