package org.greenbyme.angelhack.ui.mission.userpick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionListDAO
import org.greenbyme.angelhack.ui.BaseAdapter
import org.greenbyme.angelhack.ui.BaseAdapter.OnClickPositionListener
import org.greenbyme.angelhack.utils.Utils


class MissionPickHolder(mRecyclerView: View) :
    BaseAdapter.BaseHolder<MissionListDAO.Content>(mRecyclerView) {
    override fun getItemResId(): Int {
        return R.layout.item_mission_userpick
    }

    override fun bind(item: MissionListDAO.Content, onClickListener: OnClickPositionListener?) {
        with(itemView) {
            Glide.with(context).load(item.missionPictureUrl).into(img_mission_userpick_icon)
            tv_mission_userpick_title.text = item.missionTitle.parseAsHtml()
            tv_mission_userpick_category.text = Utils.getCategoryStringKOR(item.category)
            img_mission_userpick_icon.setBackgroundColor(15)
            tv_mission_userpick_date.text = Utils.getCategoryStringKOR(item.dayCategory)
            tv_mission_userpick_complete.text = "${item.passCandidatesCount}명 완료"
        }
    }

    override fun from(
        parent: ViewGroup,
        onClickListener: OnClickPositionListener?
    ): MissionPickHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(getItemResId(), parent, false)

        return MissionPickHolder(layoutInflater).apply {
            itemView.setOnClickListener {
                onClickListener?.onClick(it, adapterPosition)
            }
        }
    }

}