package org.greenbyme.angelhack.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BaseAdapter<T>(
    private var holder: BaseHolder<T>,
    val onClickListener: OnClickPositionListener? = null
) :
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
        return holder.from(parent, onClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseHolder<T>).bind(mItemList[position], onClickListener)
    }

    override fun getItemCount(): Int = mItemList.size

    fun getItem(position: Int): T {
        return mItemList[position]
    }

    open interface OnClickPositionListener {
        fun onClick(view: View, position: Int)
    }

    abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        fun getInflater(parent: ViewGroup): View =
            LayoutInflater.from(parent.context)
                .inflate(getItemResId(), parent, false)

        abstract fun getItemResId(): Int

        abstract fun bind(items: T, onClickListener: OnClickPositionListener? = null)

        abstract fun from(
            parent: ViewGroup,
            onClickListener: OnClickPositionListener? = null
        ): RecyclerView.ViewHolder
    }
}