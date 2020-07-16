package org.greenbyme.angelhack.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tv_itemName = itemView.findViewById<TextView?>(R.id.tv_itemName)

    fun bind(item: Item) {
        tv_itemName?.text = item.id
    }
}