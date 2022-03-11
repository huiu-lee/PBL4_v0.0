package org.techtown.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_distribution_share.view.*

class distributionAdapter (private val items: ArrayList<Data>): RecyclerView.Adapter<distributionAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: distributionAdapter.ViewHolder, position: Int) {

        val item = items[position]
        val listener= View.OnClickListener { it-> Toast.makeText(it.context, "Clicked:"+item.nickname, Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView?.context, distributionNextpgActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): distributionAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.activity_distribution_share, parent, false)
        return distributionAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        init{ itemView.setOnClickListener{}
        }

        private var view: View =v
        fun bind(listener: View.OnClickListener, item:Data){
            //view.img1.setImageDrawable(item.img1)
            view.txt1.text=item.nickname
            view.setOnClickListener(listener)

        }
    }
}