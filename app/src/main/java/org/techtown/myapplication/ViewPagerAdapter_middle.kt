//package org.techtown.myapplication
//
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//
//class ViewPagerAdapter_middle(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//
//        return when(position) {
//
//            0       ->  MainMiddle_FirstFragment()
//
//            1       ->  MainMiddle_SecondFragment()
//
//            2       ->  MainMiddle_ThirdFragment()
//
//            else       ->  MainMiddle_ForthFragment()
//
//        }
//
//    }
//
//    // 생성 할 Fragment 의 개수
//    override fun getCount() = 4
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
//    }
//
//}