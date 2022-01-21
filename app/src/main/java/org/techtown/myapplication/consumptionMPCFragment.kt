package org.techtown.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.dinuscxj.progressbar.CircleProgressBar
import java.util.*


class consumptionMPCFragment : Fragment() {

    lateinit var two_mon : TextView
    lateinit var one_mon : TextView
    lateinit var to_mon : TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_consumption_mpc, container, false)

        two_mon = view.findViewById(R.id.two_mon)
        one_mon = view.findViewById(R.id.one_mon)
        to_mon = view.findViewById(R.id.to_mon)

        val instance = Calendar.getInstance()
        var month = instance.get((Calendar.MONTH)).toString()

        var a = month.toInt() + 1
        var b = (a - 1).toString()
        var c = (a - 2).toString()
        if(b == "0") b = "12"
        if(c == "-1") c = "11"
        if(c == "0") c = "12"

        to_mon.text = a.toString() + "월"
        one_mon.text = b + "월"
        two_mon.text = c + "월"

        var mSolidProgressBar1 = view.findViewById<CircleProgressBar>(R.id.week_1)
        var mSolidProgressBar2 = view.findViewById<CircleProgressBar>(R.id.week_2)
        var mSolidProgressBar3 = view.findViewById<CircleProgressBar>(R.id.week_3)

        mSolidProgressBar1.max = 1000
        mSolidProgressBar2.max = 1000
        mSolidProgressBar3.max = 1000

        var graph_max1 = mSolidProgressBar1.max
        var graph_max2 = mSolidProgressBar2.max
        var graph_max3 = mSolidProgressBar3.max

        mSolidProgressBar1.progress = 487
        var graph_progress1 = mSolidProgressBar1.progress
        mSolidProgressBar2.progress = 910
        var graph_progress2 = mSolidProgressBar2.progress
        mSolidProgressBar3.progress = 785
        var graph_progress3 = mSolidProgressBar3.progress

        mSolidProgressBar1.setProgressFormatter { graph_progress1, graph_max1 ->
            val DEFAULT_PATTERN = "%dkw"
            String.format(DEFAULT_PATTERN, (graph_progress1.toFloat() / graph_max1.toFloat() * 1000).toInt())
        }
        mSolidProgressBar2.setProgressFormatter { graph_progress2, graph_max2 ->
            val DEFAULT_PATTERN = "%dkw"
            String.format(DEFAULT_PATTERN, (graph_progress2.toFloat() / graph_max2.toFloat() * 1000).toInt())
        }
        mSolidProgressBar3.setProgressFormatter { graph_progress3, graph_max3 ->
            val DEFAULT_PATTERN = "%dkw"
            String.format(DEFAULT_PATTERN, (graph_progress3.toFloat() / graph_max3.toFloat() * 1000).toInt())
        }


        mSolidProgressBar1.setProgressStartColor(Color.parseColor("#75A488"))
        mSolidProgressBar1.setProgressEndColor(Color.parseColor("#75A488"))
        mSolidProgressBar1.setProgressBackgroundColor(Color.parseColor("#CFDFD6"))
        mSolidProgressBar1.setProgressTextColor(Color.parseColor("#75A488"))
        mSolidProgressBar1.setProgressTextSize(60F)
        mSolidProgressBar1.setStyle(2) // LINE 0, SOLID 1, SOLID_LINE 2
        mSolidProgressBar1.setCap(Paint.Cap.ROUND)

        mSolidProgressBar2.setProgressStartColor(Color.parseColor("#75A488"))
        mSolidProgressBar2.setProgressEndColor(Color.parseColor("#75A488"))
        mSolidProgressBar2.setProgressBackgroundColor(Color.parseColor("#CFDFD6"))
        mSolidProgressBar2.setProgressTextColor(Color.parseColor("#75A488"))
        mSolidProgressBar2.setProgressTextSize(60F)
        mSolidProgressBar2.setStyle(2) // LINE 0, SOLID 1, SOLID_LINE 2
        mSolidProgressBar2.setCap(Paint.Cap.ROUND)

        mSolidProgressBar3.setProgressStartColor(Color.parseColor("#75A488"))
        mSolidProgressBar3.setProgressEndColor(Color.parseColor("#75A488"))
        mSolidProgressBar3.setProgressBackgroundColor(Color.parseColor("#CFDFD6"))
        mSolidProgressBar3.setProgressTextColor(Color.parseColor("#75A488"))
        mSolidProgressBar3.setProgressTextSize(60F)
        mSolidProgressBar3.setStyle(2) // LINE 0, SOLID 1, SOLID_LINE 2
        mSolidProgressBar3.setCap(Paint.Cap.ROUND)

        return view
    }
}