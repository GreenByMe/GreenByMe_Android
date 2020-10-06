package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.ProgressItem

class ProgressViewHolder(view: View) : HomeViewHolder<ProgressItem>(view) {
    private val mImg: ImageView = view.findViewById(R.id.img_mission_progress_bg)
    private val mDate: TextView = view.findViewById(R.id.tv_mission_progress_day)
    private val mUserCnt: TextView = view.findViewById(R.id.tv_mission_progress_user_count)
    private val mTitle: TextView = view.findViewById(R.id.tv_mission_progress_contents)
    private val mRate: TextView = view.findViewById(R.id.tv_mission_progress_percents)
    private val mCurMax: TextView = view.findViewById(R.id.tv_mission_progress_percents_count)

    override fun bind(data: ProgressItem) {
        Glide.with(itemView).load(data.img).into(mImg)
        mDate.text = data.date
        mUserCnt.text = data.userCnt
        mTitle.text = data.title
        mRate.text = data.rate
        mCurMax.text = data.curCnt.toString() + "/" + data.maxCnt.toString()

    }
}