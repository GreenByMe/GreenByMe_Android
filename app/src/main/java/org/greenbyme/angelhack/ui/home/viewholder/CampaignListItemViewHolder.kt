package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_mission_list_content.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.adapter.HomeItemClickListener
import org.greenbyme.angelhack.ui.home.model.Campaign

class CampaignListItemViewHolder(view: View) : HomeViewHolder<Campaign>(view) {
    private val mThumbnail: ImageView = view.findViewById(R.id.iv_home_mission_image)
    private val mTag: TextView = view.findViewById(R.id.tv_home_tag)
    private val mName: TextView = view.findViewById(R.id.tv_home_mission_name)
    private val mDate: TextView = view.findViewById(R.id.tv_home_mission_date)
    private val mNums: TextView = view.findViewById(R.id.tv_home_nums)

    override fun bind(data: Campaign) {
        if (data.status == "FAIL") {
            itemView.img_home_mission_ended_bg.visibility = View.VISIBLE
            itemView.tv_home_mission_ended_title.visibility = View.VISIBLE
        } else {
            itemView.img_home_mission_ended_bg.visibility = View.GONE
            itemView.tv_home_mission_ended_title.visibility = View.GONE
        }
        if (data.imageUrl.isNotBlank()) {
            Glide.with(itemView).load(data.imageUrl).into(mThumbnail)
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