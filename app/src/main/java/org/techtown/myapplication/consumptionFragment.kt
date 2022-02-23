package org.techtown.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.techtown.myapplication.databinding.FragmentConsumptionMainBinding


class consumptionFragment : Fragment() {
    lateinit var logingo : TextView
    lateinit var safe : TextView

    val database = Firebase.database

    private var _binding: FragmentConsumptionMainBinding? = null
    private val binding get() = _binding!!

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

        logingo = view.findViewById(R.id.logingo)
        safe = view.findViewById(R.id.safe)

        var n = 0

        var myRef1 = database.getReference("user1").child("measure")
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
        var myRef2 = database.getReference("user1").child("middle")
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

                    var myRef1 = database.getReference("user1").child("measure")
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
                    var myRef2 = database.getReference("user1").child("middle")

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

                    var myRef1 = database.getReference("user2").child("measure")
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
                    var myRef2 = database.getReference("user2").child("middle")

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
                    var myRef1 = database.getReference("user3").child("measure")

                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("user3").child("middle")

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
                    var myRef1 = database.getReference("user1").child("measure")

                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("user1").child("middle")
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
                    var myRef1 = database.getReference("user2").child("measure")
                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("user2").child("middle")

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
                    var myRef1 = database.getReference("user3").child("measure")

                    myRef1.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot?.value
                            c_measure.text = value.toString() + "W"
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })
                    var myRef2 = database.getReference("user3").child("middle")

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

        logingo.setOnClickListener {
            var intent = Intent(view.context, LoginActivity::class.java)
            startActivity(intent)
        }
        safe.setOnClickListener {
            var intent = Intent(view.context, Logout_Activity::class.java)
            startActivity(intent)
        }


        return view
    }
}