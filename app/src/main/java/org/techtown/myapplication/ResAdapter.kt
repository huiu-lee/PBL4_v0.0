package org.techtown.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResAdapter (private val context: resourceFragment): RecyclerView.Adapter<ResAdapter.ViewHolder>() {

    private var userList = mutableListOf<Data>()

    fun setListData(data:MutableList<Data>){
        userList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResAdapter.ViewHolder {

        var view : View?
        var view2 : View?

        return when(viewType){
            1 -> {
                view2 = LayoutInflater.from(parent.context).inflate(
                    R.layout.item,
                    parent,
                    false
                )
                ViewHolder(view2).apply {
                    itemView.setOnClickListener {
                        var intent = Intent(view2.context, resourcePlantActivity::class.java)
                        intent.putExtra("name", res_name.text)
                        view2.context.startActivity(intent)
                    }
                }
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_r,
                    parent,
                    false
                )
                ViewHolder(view).apply {
                    itemView.setOnClickListener {
                        var intent = Intent(view.context, resourcePlantActivity_red::class.java)
                        intent.putExtra("name", res_name.text)
                        view.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ResAdapter.ViewHolder, position: Int) {
        val user : Data = userList[position]
        holder.res_name.text = user.email
        holder.res_mea.text = user.measure.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val res_name : TextView = itemView.findViewById(R.id.res_name)
        val res_mea : TextView = itemView.findViewById(R.id.res_mea)
    }

    override fun getItemViewType(position: Int): Int {
        return userList[position].status
    }
}