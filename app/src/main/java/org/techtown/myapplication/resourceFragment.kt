package org.techtown.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_resource.*

class resourceFragment : Fragment() {

    lateinit var powerPlant2: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.activity_resource, container, false)
        powerPlant2 = view.findViewById(R.id.powerPlant2)
        powerPlant2.setOnClickListener{
            var intent = Intent(view.context, resourcePlantActivity::class.java)
            startActivity(intent)
        }

        return view
    }



}