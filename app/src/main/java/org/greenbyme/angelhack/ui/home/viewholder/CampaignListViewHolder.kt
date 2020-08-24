package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.CampaignList

class CampaignListViewHolder(view: View) : HomeViewHolder<CampaignList>(view) {
    private val mTitle: TextView = view.findViewById(R.id.tv_home_mission)
    private val mAdapter = HomeAdapter()

    init {
        view.findViewById<RecyclerView>(R.id.rv_home_mission).run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = mAdapter
        }
    }

    override fun bind(data: CampaignList) {
        mTitle.text = data.title
        mAdapter.setItems(data.campaignList)
    }

    override fun bind(data: CampaignList, itemClickListener: HomeItemClickListener?) {
        super.bind(data, itemClickListener)
        mAdapter.itemClickListener = itemClickListener
        itemView.setOnClickListener {
            if (data.type == CampaignList.Type.POPULAR) {
                itemClickListener?.onPopularCampaignClicked()
            } else {
                itemClickListener?.onCampaignClicked()
            }
        }
    }
}