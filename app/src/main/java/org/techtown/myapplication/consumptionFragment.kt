package org.techtown.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.myapplication.databinding.FragmentConsumptionMainBinding


class consumptionFragment : Fragment() {
    val database = Firebase.database

    lateinit var database2 : FirebaseDatabase
    lateinit var databaseReference2: DatabaseReference

    lateinit var btn_back: ImageView
    lateinit var btn_forward: ImageView

    lateinit var c_middle : TextView
    lateinit var c_measure : TextView

    lateinit var main_month: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_consumption_main, container, false)

        btn_back = view.findViewById(R.id.btn_back)
        btn_forward = view.findViewById(R.id.btn_forward)

        c_middle = view.findViewById(R.id.c_middle)
        c_measure = view.findViewById(R.id.c_measure)

        main_month = view.findViewById(R.id.main_month)

        var w_t = view.findViewById<TextView>(R.id.w_t)

        // 툴바 사용
        var n = 0

        var myRef1 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("measure")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef1.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                c_measure.text = value.toString() + "W"
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        var myRef2 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("middle")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                c_middle.text = value.toString() + "W"
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        btn_forward.setOnClickListener {
            if (n == 0 || n == 1) {
                n++
            }

            when (n) {
                0 -> {
                    main_month.text = "가구1"

                    var myRef1 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("measure")
                    //특정 데이터 값 갖고 오기!
                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString()  + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("middle")

                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
                1 -> {
                    main_month.text = "가구2"

                    var myRef1 = database.getReference("Users").child("users").child("-MwCYBGzYzjVVbwU5yW_").child("measure")
                    //특정 데이터 값 갖고 오기!
                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwCYBGzYzjVVbwU5yW_").child("middle")

                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
                2 -> {
                    main_month.text = "가구3"

                    //특정 데이터 값 갖고 오기!
                    var myRef1 = database.getReference("Users").child("users").child("-MwEUIbjtRA4j1wQjo_3").child("measure")

                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwEUIbjtRA4j1wQjo_3").child("middle")

                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
            }
        }
        btn_back.setOnClickListener {
            if (n == 2 || n == 1) {
                n--
            }


            when (n) {
                0 -> {
                    main_month.text = "가구1"

                    //특정 데이터 값 갖고 오기!
                    var myRef1 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("measure")

                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwCVkmDQ7lbUpG05BRH").child("middle")
                    //특정 데이터 값 갖고 오기!
                    //리얼타임 데이터베이스 읽기
                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
                1 -> {
                    main_month.text = "가구2"

                    //특정 데이터 값 갖고 오기!
                    var myRef1 = database.getReference("Users").child("users").child("-MwCYBGzYzjVVbwU5yW_").child("measure")
                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwCYBGzYzjVVbwU5yW_").child("middle")

                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
                2 -> {
                    main_month.text = "가구3"

                    //특정 데이터 값 갖고 오기!
                    var myRef1 = database.getReference("Users").child("users").child("-MwEUIbjtRA4j1wQjo_3").child("measure")


                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("Users").child("users").child("-MwEUIbjtRA4j1wQjo_3").child("middle")

                    myRef2.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_middle.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                }
            }
        }
        main_month.setOnClickListener {
            var main_name = main_month.text

            var intent = Intent(view.context, consumptionDetailActivity::class.java)
            intent.putExtra("name", main_name)
            startActivity(intent)
        }
//
//        w_t.setOnClickListener {
//            var intent = Intent(view.context, weather_Test::class.java)
//            startActivity(intent)
//        }

        var x = 0
        var a = ""
        var b = ""

        var list = java.util.ArrayList<User>()

        database2 = FirebaseDatabase.getInstance()
        databaseReference2 = database2.getReference("Users").child("users")

        databaseReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.measure > 0) {
                        list.add(value!!)
                        a += list[x].measure.toString() + " "
                        //hj.text = list.size.toString()
                        x++
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        return view
    }
}