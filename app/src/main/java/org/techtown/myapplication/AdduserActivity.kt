package org.techtown.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.techtown.myapplication.databinding.ActivityAdduserBinding

class AdduserActivity : AppCompatActivity() {

    lateinit var binding : ActivityAdduserBinding
    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdduserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")

        binding.backlogin.setOnClickListener {

            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val safeNum = binding.editTextsafeNumber.text.toString().trim()

            // 이메일, 패스워드, 안심번호 입력
            if (email.isEmpty() || password.isEmpty() || safeNum.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            } else {
                if (isValidEmail(email)) {

                    var id = databaseReference.push().key
                    val User = User(email, password, safeNum, id!!, measure = 0)

                    //Data Inserted
                    databaseReference.child(id).setValue(User)
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Check your email address", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 이메일 유효성 검사
    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}