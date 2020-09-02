package org.greenbyme.angelhack.ui.mission.userpick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MainMissionDAO
import org.greenbyme.angelhack.utils.Utils

class MissionPickHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: MainMissionDAO.Content) {
        with(itemView) {
            Glide.with(context).load(item.pictureUrl).into(img_mission_userpick_icon)
            tv_mission_userpick_title.text = item.title.parseAsHtml()
            tv_mission_userpick_category.text = item.description.parseAsHtml()
            img_mission_userpick_icon.setBackgroundColor(15)
            tv_mission_userpick_date.text = Utils.getCategoryStringKOR(item.dayCategory)
            tv_mission_userpick_complete.text= "${item.passCandidates}명 완료"
        }
    }

    companion object {
        fun from(parent: ViewGroup): MissionPickHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mission_userpick, parent, false)

            return MissionPickHolder(
                layoutInflater
            ).apply {
                itemView.setOnClickListener {

                }
            }
        }
    }
}