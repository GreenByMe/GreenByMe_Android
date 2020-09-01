package org.greenbyme.angelhack.ui.mission.userpick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_mission_userpick.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionDAO

class MissionPickHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: MissionDAO) {
        with(itemView) {
            tv_mission_userpick_title.text = item.missionContents
            tv_mission_userpick_category.text = item.missionDescription
            img_mission_userpick_icon.setBackgroundColor(15)
        }
    }

    companion object {
        fun from(parent: ViewGroup): MissionPickHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mission_userpick, parent, false)

            return MissionPickHolder(
                layoutInflater
            )
        }
    }
}