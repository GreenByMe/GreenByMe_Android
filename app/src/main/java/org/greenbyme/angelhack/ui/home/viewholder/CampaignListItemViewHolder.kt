package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.Campaign

class CampaignListItemViewHolder(view: View) : HomeViewHolder<Campaign>(view) {
    private val mPicasso = Picasso.get()

    private val mThumbnail: ImageView = view.findViewById(R.id.iv_home_mission_image)
    private val mTag: TextView = view.findViewById(R.id.tv_home_tag)
    private val mName: TextView = view.findViewById(R.id.tv_home_mission_name)
    private val mDate: TextView = view.findViewById(R.id.tv_home_mission_date)
    private val mNums: TextView = view.findViewById(R.id.tv_home_nums)

    override fun bind(data: Campaign) {
        if (data.imageUrl.isNotBlank()) {
            mPicasso.load(data.imageUrl).into(mThumbnail)
        }
        mName.text = data.title
        mTag.run {
            text = "${data.progress}% 진행"
            visibility = if (data.progress > 0) View.VISIBLE else View.GONE
        }
        mDate.text = data.getDueDate()
        mNums.text = "${data.memberCount}명 도전 중"
    }

    override fun bind(data: Campaign, itemClickListener: HomeItemClickListener?) {
        super.bind(data, itemClickListener)

        itemView.setOnClickListener {
            itemClickListener?.onMissionClicked(data.id, data.missionType)
        }
    }
}