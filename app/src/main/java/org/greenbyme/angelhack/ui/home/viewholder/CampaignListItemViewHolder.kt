package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.CampaignListItem

class CampaignListItemViewHolder(view: View) : HomeViewHolder<CampaignListItem>(view) {

    private val mPicasso = Picasso.get()

    private val mThumbnail: ImageView = view.findViewById(R.id.iv_home_mission_image)
    private val mTag: TextView = view.findViewById(R.id.tv_home_tag)
    private val mName: TextView = view.findViewById(R.id.tv_home_mission_name)
    private val mData: TextView = view.findViewById(R.id.tv_home_mission_date)
    private val mNums: TextView = view.findViewById(R.id.tv_home_nums)

    override fun bind(data: CampaignListItem) {
        mPicasso.load(data.thumbnail).into(mThumbnail)
        mName.text = data.name
        mTag.run {
            text = data.tagText
            visibility = if (data.tagText.isNotBlank()) View.VISIBLE else View.GONE
        }
        mData.text = data.data
        mNums.text = data.nums
    }
}