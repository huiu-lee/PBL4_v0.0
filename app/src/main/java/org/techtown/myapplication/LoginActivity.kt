package org.techtown.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.safe_dialog.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")

        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(MySharedPreferences.getUserId(this).isNullOrBlank()
            || MySharedPreferences.getUserPass(this).isNullOrBlank()) {

            loginbut.setOnClickListener {

                var email = editTextTextEmailAddress.text.toString().trim()
                var password = editTextTextPassword.text.toString().trim()

                if(email.isNullOrBlank() || password.isNullOrBlank()) {
                    Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
                } else {
                    if (isValidEmail(email)) {
                        isEmailExist(email, password)
                        MySharedPreferences.setUserId(this, email)
                        MySharedPreferences.setUserPass(this, password)
                    }

                    Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                    var intent = Intent(this, mainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } else { // SharedPreferences 안에 값이 저장되어 있을 때 -> MainActivity로 이동
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, mainActivity::class.java)
            startActivity(intent)

            finish()
        }


        adduserbut.setOnClickListener {
            var intent = Intent(this, AdduserActivity::class.java)
            startActivity(intent)
        }

        // 로그아웃 버튼이지만 거래 페이지가 덜 개발되어서 일시적으로 안심번호 팝업창을 뜨게 함
        logout.setOnClickListener {
            var intent = Intent(this, Logout_Activity::class.java)
            startActivity(intent)

            showCustomAlert()
        }

    }

    // 이메일 유효성 검사
    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    // 입력된 이메일과 데베에 저장된 이메일 비교
    private fun isEmailExist(email: String, password: String) {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var list = ArrayList<User>()
                var isemailexist = false
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.email == email && value!!.password == password) {
                        isemailexist = true
                    }
                    list.add(value!!)
                }

                if (isemailexist) {
                    startActivity(Intent(applicationContext, mainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed! check your email address and password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    // 안심번호 팝업창 보여주기
    private fun showCustomAlert() {
        var builder = AlertDialog.Builder(this)
        // xml파일로 커스텀 다이얼로그 디자인을 보여줄때
        var v1 = layoutInflater.inflate(R.layout.safe_dialog, null)
        builder.create()

        var safeEdit = v1.findViewById<EditText>(R.id.safeEdit)
        var safeYes = v1.findViewById<TextView>(R.id.safeYes)

        safeYes.setOnClickListener {

            var safeNum = safeEdit.text.toString().trim()

            if (safeNum.isEmpty()) {
                Toast.makeText(this, "Safe Number required", Toast.LENGTH_SHORT).show()
            } else {
                if (isValidSafeNum(safeNum)) {
                    isSafeNumExist(safeNum)
                } else {
                    Toast.makeText(this, "Check your safe number", Toast.LENGTH_SHORT).show()
                }
            }
        }

        builder.setView(v1)
        builder.show()

    }

    // 안심번호 유효성 검사
    private fun isValidSafeNum(safeNum: String): Boolean {
        return Pattern.matches("^[0-9]*\$", safeNum)
    }

    // 입력된 안심번호와 데베에 저장된 안심번호 비교
    private fun isSafeNumExist(safeNum: String) {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var list = java.util.ArrayList<User>()
                var issafenumexist = false
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.safeNum == safeNum) {
                        issafenumexist = true
                    }
                    list.add(value!!)
                }

                if (issafenumexist) {
                    Toast.makeText(this@LoginActivity, "Safe number match", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, mainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "Safe number not match! check your safe number", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}