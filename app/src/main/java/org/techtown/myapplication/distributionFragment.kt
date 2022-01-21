package org.techtown.myapplication

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_distribution.*

class distributionFragment : Fragment() {
    val database = Firebase.database

    val list=ArrayList<Data>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_distribution, container, false)

        list.add(Data("가구1"))
        list.add(Data("가구2"))
        list.add(Data("가구3"))
        list.add(Data("가구4"))
        list.add(Data("가구5"))
        list.add(Data("가구6"))
        list.add(Data("가구7"))
        list.add(Data("가구8"))
        list.add(Data("가구9"))

        return view
    }
    //프래그먼트 내 리사이클러뷰와 리스트의 아이템들을 연결해주는 코드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter=distributionAdapter(list)
        distri_recycle.adapter=adapter

        val lm = LinearLayoutManager(context)
        distri_recycle.layoutManager=lm
        distri_recycle.setHasFixedSize(true)
    }
}