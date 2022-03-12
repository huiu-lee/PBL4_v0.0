package org.techtown.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.main.MySharedPreferences
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_consumption_main.*
import kotlinx.android.synthetic.main.fragment_mypage.*

class mypageFragment : Fragment() {

    lateinit var logout_btn : Button
    lateinit var name : TextView
    lateinit var point : TextView

    lateinit var btn_n1 : TextView
    lateinit var btn_n2 : ImageView
    lateinit var btn_n3 : ImageView
    lateinit var btn_nick : TextView

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_mypage, container, false)

        logout_btn = view.findViewById(R.id.logout_btn)
        name = view.findViewById(R.id.name)
        point = view.findViewById(R.id.point)

        btn_n1 = view.findViewById(R.id.btn_n)
        btn_n2 = view.findViewById(R.id.btn_n2)
        btn_n3 = view.findViewById(R.id.btn_n3)
        btn_nick = view.findViewById(R.id.btn_nick)

        var x = MySharedPreferences.getUserPass(view.context)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Users").child("users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.password == x) {
                        point.text = value.point.toString()
                        name.text = value.nickname
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        logout_btn.setOnClickListener{
            // fragment에서 SharedPreferences 사용시 선언
            val preferences = this.activity!!
                .getSharedPreferences("pref", 0)

            // requireContext() = this - activity에서
            MySharedPreferences.clearUser(requireContext())

            // fragment에서 intent로 activity를 넘어감
            activity?.let{
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }

            // activity의 finish()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }
        btn_n1.setOnClickListener {
            var intent = Intent(view.context, noticeActivity::class.java)
            startActivity(intent)
        }

        btn_n3.setOnClickListener {
            var intent = Intent(view.context, noticeActivity::class.java)
            startActivity(intent)
        }

        btn_nick.setOnClickListener {
            var intent = Intent(view.context, nickname_modify_activity::class.java)
            startActivity(intent)
        }
        btn_n2.setOnClickListener {
            var intent = Intent(view.context, nickname_modify_activity::class.java)
            startActivity(intent)
        }
        return view
    }


}

