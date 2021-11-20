package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_trade_process.*
import java.util.*

class TradeProcessActivity : AppCompatActivity() {
    lateinit var dateSetBtn : Button
    var dateString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_process)
        dateSetBtn = findViewById<Button>(R.id.dateSetBtn)

        dateSetBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateString = "${year}-${month+1}-${dayOfMonth}"
                dateSetBtn.text = dateString
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}



