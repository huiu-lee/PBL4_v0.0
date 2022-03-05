package org.techtown.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class notiAdapter(private val context: noticeActivity): RecyclerView.Adapter<notiAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Data_noti>()

    fun setListData(data:MutableList<Data_noti>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notiAdapter.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                var intent = Intent(view.context, notice_detail_Activity::class.java)

                var x = title.text.toString()
                var y = content.text.toString()
                var myExam = Exam(x,y)

                intent.putExtra("examKey", myExam)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: notiAdapter.ViewHolder, position: Int) {
        val noti : Data_noti = dataList[position]
        holder.title.text = noti.title
        holder.content.text = noti.content
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val content : TextView = itemView.findViewById(R.id.content)
    }
}