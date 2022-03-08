package org.techtown.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repo_n {

    fun getData(): LiveData<MutableList<Data_noti>> {

        val mutableData = MutableLiveData<MutableList<Data_noti>>()
        val database = Firebase.database
        val myRef = database.getReference("Notice").child("Notice")

        myRef.addValueEventListener(object : ValueEventListener {

            val listData: MutableList<Data_noti> = mutableListOf<Data_noti>()

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data_noti::class.java)
                        listData.add(0,getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        return mutableData
    }
}