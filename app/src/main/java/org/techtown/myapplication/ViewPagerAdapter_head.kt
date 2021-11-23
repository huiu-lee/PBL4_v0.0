//package org.techtown.myapplication
//
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//
//class ViewPagerAdapter_head(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//
//        return when(position) {
//
//            0       ->  MainHead_FirstFragment()
//
//            1       ->  MainHead_SecondFragment()
//
//            else       ->  MainHead_ThirdFragment()
//
//        }
//
//    }
//
//    // 생성 할 Fragment 의 개수
//    override fun getCount() = 3
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
//    }
//
//}