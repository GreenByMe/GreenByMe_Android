package org.greenbyme.angelhack.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.HomeItemViewType
import org.greenbyme.angelhack.ui.home.viewholder.*

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder<HomeItem>>() {
    private val mItemList = mutableListOf<HomeItem>()

    var itemClickListener: HomeItemClickListener? = null

    fun setItems(items: List<HomeItem>) {
        synchronized(mItemList) {
            mItemList.run {
                clear()
                addAll(items)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mItemList[position].getViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder<HomeItem> {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (HomeItemViewType.get(viewType)) {
            HomeItemViewType.HEADER -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.item_home_header, parent, false))
            }
            HomeItemViewType.CAMPAIGN_LIST -> {
                CampaignListViewHolder(
                    layoutInflater.inflate(
                        R.layout.row_horizontal_list,
                        parent,
                        false
                    )
                )
            }
            HomeItemViewType.CAMPAIGN_LIST_ITEM -> {
                CampaignListItemViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_home_mission,
                        parent,
                        false
                    )
                )
            }

            HomeItemViewType.CAMPAIGN_DETAIL_ITEM -> {
                ProgressViewHolder(layoutInflater.inflate(
                    R.layout.item_mission_progress,
                    parent,
                    false
                ))
            }

            else -> {
                EmptyViewHolder(layoutInflater.inflate(R.layout.row_empty, parent, false))
            }
        } as HomeViewHolder<HomeItem>
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder<HomeItem>, position: Int) {
        holder.bind(mItemList[position], itemClickListener)
    }
}