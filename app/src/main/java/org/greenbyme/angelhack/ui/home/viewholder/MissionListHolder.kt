package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.utils.Utils

class MissionListHolder(mRecyclerView: View) :
    BaseAdapter.BaseHolder<MainMissionDAO.Content>(mRecyclerView) {
    override fun getItemResId(): Int {
        return R.layout.item_mission_userpick
    }

    override fun bind(
        item: MainMissionDAO.Content,
        onClickListener: BaseAdapter.OnClickPositionListener?
    ) {
        with(itemView) {
            Glide.with(context).load(item.missionPictureUrl).into(img_mission_userpick_icon)
            tv_mission_userpick_title.text = item.title.parseAsHtml()
            tv_mission_userpick_category.text = "#" + Utils.getCategoryStringKOR(item.category)
            img_mission_userpick_icon.setBackgroundColor(15)
            tv_mission_userpick_date.text = Utils.getCategoryStringKOR(item.dayCategory)
            tv_mission_userpick_complete.text = "${item.passCandidates}명 완료"
        }
    }

    override fun from(
        parent: ViewGroup,
        onClickListener: BaseAdapter.OnClickPositionListener?
    ): MissionListHolder {
        return MissionListHolder(getInflater(parent)).apply {
            itemView.setOnClickListener {
                onClickListener?.onClick(it, adapterPosition)
            }
        }
    }

}