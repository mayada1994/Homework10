package com.example.mayada.geekhubandroidprofiler.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mayada.geekhubandroidprofiler.R
import com.example.mayada.geekhubandroidprofiler.activities.MainActivity
import com.example.mayada.geekhubandroidprofiler.entities.Item

class ItemAdapter(val items: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemNumber.text = (position + 1).toString()
        viewHolder.itemName.text = items[position].userName
        viewHolder.itemLogin = items[position].userLogin
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNumber: TextView = itemView.findViewById(R.id.item_number)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
        lateinit var itemLogin:String

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MainActivity::class.java)
                intent.putExtra("profileName", itemName.text)
                intent.putExtra("profileLogin", itemLogin)
                itemView.context.startActivity(intent)
            }
        }

    }
}
