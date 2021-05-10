package com.example.awol.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.awol.DataObjectRestaurant
import com.example.awol.R
import com.example.awol.RestaurantDetail
import com.example.awol.login_signup.LoginActivity
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
        val tvRestaurantName: TextView
        val tvRestaurantAddress: TextView
        val restaurantCard: RelativeLayout

        init {
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName)
            tvRestaurantAddress = itemView.findViewById(R.id.tvRestaurantAddress)
            restaurantCard = itemView.findViewById(R.id.restaurantCard)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RestaurantDetail::class.java)
                intent.putExtra("restoName" ,data[adapterPosition].name)
                intent.putExtra("restoAddress", data[adapterPosition].alamat)
                intent.putExtra("restoWorkingHour", data[adapterPosition].workingHour)
                intent.putExtra("restoImage", data[adapterPosition].images)
                itemView.context.startActivity(intent)
            }
        }
    }
}