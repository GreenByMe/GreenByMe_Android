package org.greenbyme.angelhack.ui.mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_mission_tag.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionTagDAO

class MissionTagAdapter(
    private val list: ArrayList<MissionTagDAO> = makeDummy(),
    private val tagListener: TagOnClickListener,
    private val isSelected: Boolean = false
) :
    RecyclerView.Adapter<MissionTagAdapter.Holder>() {
    private var fontColor = R.color.tag_color

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            if (isSelected) {
                fontColor = R.color.white
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_mission_tag_circle, parent, false)
            } else {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_mission_tag, parent, false)
            }

        return Holder(view).apply {
            itemView.setOnClickListener {
                if (prePosition != adapterPosition) {
                    tagListener.onClickTag(adapterPosition)
                    list[prePosition].isSelected = false
                    list[adapterPosition].isSelected = true
                }

                if (list[adapterPosition].isSelected) {
                    missionTag.setTextColor(itemView.resources.getColor(R.color.colorPrimary))
                } else {
                    missionTag.setTextColor(itemView.resources.getColor(fontColor))
                }
                prePosition = adapterPosition
                notifyDataSetChanged()
            }
        }
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.missionTag.apply {
            if (list[position].isSelected) {
                setTextColor(resources.getColor(R.color.colorPrimary))
            } else {
                setTextColor(resources.getColor(fontColor))
            }
            text = "#" + list[position].missionTagName
        }
    }

    companion object {
        var prePosition: Int = 0
        fun makeDummy(category: Int = 0): ArrayList<MissionTagDAO> {
            val dummy = ArrayList<MissionTagDAO>()
            dummy.add(MissionTagDAO(missionTagCategory = 0, missionTagName = "전체"))
            dummy.add(MissionTagDAO(missionTagCategory = 1, missionTagName = "에너지"))
            dummy.add(MissionTagDAO(missionTagCategory = 2, missionTagName = "일회용"))
            dummy.add(MissionTagDAO(missionTagCategory = 3, missionTagName = "교통"))
            dummy.add(MissionTagDAO(missionTagCategory = 4, missionTagName = "수자원"))
            dummy.add(MissionTagDAO(missionTagCategory = 5, missionTagName = "캠페인"))
            dummy[category].isSelected = true
            prePosition = category
            return dummy
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val missionTag: TextView = view.tv_mission_tag_title
    }
}