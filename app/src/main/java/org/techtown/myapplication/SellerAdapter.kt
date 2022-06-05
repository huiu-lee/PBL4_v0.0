package org.techtown.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SellerAdapter(private val sellerList : ArrayList<Seller>) : RecyclerView.Adapter<SellerAdapter.SellerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.seller_item,
            parent, false)

        return SellerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        val currentitem = sellerList[position]

        holder.sel_powerplant.text = currentitem.Selpowerplant
        holder.sel_address.text = currentitem.Seladdress
        holder.sel_location.text = currentitem.Sellocation
        //holder.howmuch.text = currentitem.SelEditpower

    }

    override fun getItemCount(): Int {

        return sellerList.size
    }

    class SellerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val sel_powerplant : TextView = itemView.findViewById(R.id.sel_powerplant)
        val sel_address : TextView = itemView.findViewById(R.id.sel_address)
        val sel_location : TextView = itemView.findViewById(R.id.sel_location)
    }
}