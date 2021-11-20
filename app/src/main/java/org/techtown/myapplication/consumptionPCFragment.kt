package org.techtown.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class consumptionPCFragment : Fragment() {

    lateinit var c_middle : TextView
    lateinit var c_measure : TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_consumption_pc, container, false)

        c_middle = view.findViewById(R.id.c_middle)
        c_measure = view.findViewById(R.id.c_measure)

        val db = Firebase.firestore

        //특정 데이터 값 갖고 오기!
        db.collection("users").whereEqualTo("name", "가구1").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var x = document["measure"] as Number
                    var y = document["middle"] as Number
                    c_measure.text = x.toString() + "kw"
                    c_middle.text = "평균 " + y.toString() + "KW"
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}