package org.greenbyme.angelhack.ui
//
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//
//class Base2Adapter() :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val mItemList = mutableListOf<Base2Holder>()
//
//    fun setItems(items: List<Base2Holder>) {
//        synchronized(mItemList) {
//            mItemList.run {
//                clear()
//                addAll(items)
//            }
//            notifyDataSetChanged()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val empty = object : RecyclerView.ViewHolder(parent){}
//        val position = empty.adapterPosition
//        return mItemList[position].from(parent)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as Base2Holder).bind(mItemList[position].getItem())
//    }
//
//    override fun getItemCount(): Int = mItemList.size
//
//
//    interface Base2Holder {
//        fun bind(items: Unit)
//        fun from(parent: ViewGroup): RecyclerView.ViewHolder
//        fun getItem(): Unit
//    }
//}