package com.example.mayada.geekhubandroidprofiler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemAdapter(val items: ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemNumber.text = (position + 1).toString()
        viewHolder.itemName.text = items[position].text
//        viewHolder.itemIcon.id = items[position].imageResourceId
//        when (viewHolder.itemIcon.id) {
//            -1 -> viewHolder.itemIcon.visibility = View.GONE
//            else -> {
//                viewHolder.itemIcon.setImageResource(viewHolder.itemIcon.id)
//                viewHolder.itemIcon.visibility = View.VISIBLE
//            }
//        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNumber: TextView = itemView.findViewById(R.id.item_number)
//        var itemIcon: ImageView = itemView.findViewById(R.id.item_icon)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
    }
}