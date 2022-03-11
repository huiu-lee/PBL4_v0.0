package org.techtown.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class nickname_modify_activity : AppCompatActivity() {

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    lateinit var nick_back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname_modify)

        var x = MySharedPreferences.getUserPass(this)

        var nicknameEdit = findViewById<EditText>(R.id.nicknameEdit)
        var nicknameYes = findViewById<TextView>(R.id.nicknameYes)

        nick_back = findViewById(R.id.nick_back)



        nicknameYes.setOnClickListener {
            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("Users").child("users")
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postsnapshot in dataSnapshot.children) {

                        var value = postsnapshot.getValue<User>()

                        var nickname = nicknameEdit.text.toString()

                        if (value!!.password == x) {
                            var myRef1 = database.getReference("Users").child("users").child(value.id).child("nickname")
                            myRef1.setValue(nickname)

                            Toast.makeText(this@nickname_modify_activity, "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            })
            finish()
        }
        nick_back.setOnClickListener {
            finish()
        }
    }
}