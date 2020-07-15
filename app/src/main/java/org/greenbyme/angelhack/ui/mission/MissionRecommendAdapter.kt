package org.greenbyme.angelhack.ui.mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_mission_tag.view.*
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.data.MissionTagDAO
import java.util.*
import kotlin.collections.ArrayList

class MissionTagAdapter(private val list: ArrayList<MissionTagDAO>) :
    RecyclerView.Adapter<MissionTagAdapter.Holder>() {

    companion object{
        fun makeDummy() :ArrayList<MissionTagDAO>{
            val dummy = ArrayList<MissionTagDAO>()
            dummy.add(MissionTagDAO())
            dummy.add(MissionTagDAO())
            dummy.add(MissionTagDAO())
            return dummy
        }
    }
    
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mission_tag, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.missionTag.text = list[position].missionTagName
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val missionTag: TextView = view.tv_mission_tag_title
    }
}