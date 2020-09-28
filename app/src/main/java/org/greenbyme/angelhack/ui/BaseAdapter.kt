package org.greenbyme.angelhack.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BaseAdapter<T>(val mBaseHolder: BaseHolder<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mItemList = mutableListOf<T>()

    fun setItems(items: List<T>) {
        synchronized(mItemList) {
            mItemList.run {
                clear()
                addAll(items)
            }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mBaseHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseHolder<T>).bind(mItemList[position])
    }

    override fun getItemCount(): Int = mItemList.size

    interface BaseHolder<T> {
        fun bind(items: T)
        fun from(parent: ViewGroup): RecyclerView.ViewHolder
    }
}