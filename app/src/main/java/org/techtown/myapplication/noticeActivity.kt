package org.techtown.myapplication

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_notice.*

class noticeActivity : AppCompatActivity() {

    private lateinit var notiadapter : notiAdapter

    lateinit var page_rec : RecyclerView

    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        page_rec =findViewById(R.id.page_rec)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ListViewModel_n::class.java)

        notiadapter = notiAdapter(this)

        val recyclerView : RecyclerView = findViewById(R.id.page_rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notiadapter
        observerData(viewModel)
    }

    fun observerData(viewModel: ListViewModel_n){
        viewModel.fetchData().observe(this, Observer {
            notiadapter.setListData(it)
            notiadapter.notifyDataSetChanged()
        })

        // 뒤로가기 버튼
        back_no.setOnClickListener{
            finish()
        }
    }

}