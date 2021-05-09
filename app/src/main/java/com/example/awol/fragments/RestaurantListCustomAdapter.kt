package com.example.awol.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.awol.DataObjectRestaurant
import com.example.awol.R
import org.w3c.dom.Text

class RestaurantListCustomAdapter(
    private val data:List<DataObjectRestaurant>
): RecyclerView.Adapter<RestaurantListCustomAdapter.ViewHolder>() {

    private val itemClass: MutableList<RelativeLayout>

    init {
        this.itemClass = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.restauran_cards, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRestaurantName.text = data[position].name
        holder.tvRestaurantAddress.text = data[position].alamat
        itemClass.add(holder.restaurantCard)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val tvRestaurantName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        val tvRestaurantAddress: TextView = itemView.findViewById(R.id.tvRestaurantAddress)
        val restaurantCard: RelativeLayout =itemView.findViewById(R.id.restaurantCard)
    }
}