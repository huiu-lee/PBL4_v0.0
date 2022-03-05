package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class notice_detail_Activity : AppCompatActivity() {

    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        var d_title = findViewById<TextView>(R.id.d_title)
        var d_content = findViewById<TextView>(R.id.d_content)

        var x = ""
        var y = ""

        if (intent.hasExtra("examKey")) {
            var exam = intent.getParcelableExtra<Exam>("examKey")
            // exam변수를 초기화 할 때 자료형이 정해지지 않아서 getParcelableExtra 뒤에 <>가 생기고, 이곳에 자료형을 입력한다. */

            //exam에서 받은 값들을 변수에 저장시킴
            if (exam != null) {
                x = exam.title
                y = exam.content
            }
        }

        d_title.text = x
        d_content.text = y

    }
}