package org.greenbyme.angelhack.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.HomeItem
import org.greenbyme.angelhack.ui.home.model.HomeItemViewType
import org.greenbyme.angelhack.ui.home.viewholder.*

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItemList = mutableListOf<HomeItem>()

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (HomeItemViewType.get(viewType)) {
            HomeItemViewType.HEADER -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.item_home_header, parent, false))
            }
            HomeItemViewType.HORIZONTAL_LIST -> {
                HorizontalListViewHolder(
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

            HomeItemViewType.NEWS_LIST -> {
                NewsViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_home_news,
                        parent,
                        false
                    )
                )
            }

            HomeItemViewType.NEWS_LIST_ITEM -> {
                NewsListItemViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_home_news_contents,
                        parent,
                        false
                    )
                )
            }

            HomeItemViewType.CERTIFICATION_LIST -> {
                CertificationViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_home_certification,
                        parent,
                        false
                    )
                )
            }

            HomeItemViewType.CERTIFICATION_LIST_ITEM -> {
                GridListViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_home_mission_img,
                        parent,
                        false
                    )
                )
            }

            else -> {
                EmptyViewHolder(layoutInflater.inflate(R.layout.row_empty, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? HomeViewHolder<HomeItem>)?.bind(mItemList[position])
    }
}