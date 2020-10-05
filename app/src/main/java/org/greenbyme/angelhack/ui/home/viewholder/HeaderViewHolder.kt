package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.parseAsHtml
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.User


class HeaderViewHolder(view: View) : HomeViewHolder<User>(view) {
    private val mUserName: TextView = view.findViewById(R.id.tv_home_name)
    private val mSentence: TextView = view.findViewById(R.id.tv_home_desc)
    private val mCampaign: TextView = view.findViewById(R.id.campaign_count_title)
    private val mCo2: TextView = view.findViewById(R.id.co_title)
    private val mTree: TextView = view.findViewById(R.id.tree_count_title)
    private var mPercent: ProgressBar = view.findViewById(R.id.pb_home_mission_progress)
    private var mPercent_layout: ConstraintLayout =
        view.findViewById(R.id.layout_home_mission_progress)
    private var mPercent_text: TextView = view.findViewById(R.id.tv_home_mission_progress_text)


    override fun bind(data: User) {
        mUserName.text = "안녕하세요 ${data.nickName} 님"
        mSentence.text = data.treeSentence.parseAsHtml()
        mCampaign.text = data.progressCampaign.toString()
        mCo2.text = data.expectedCO2.toString()
        mTree.text = data.expectedTree.toString()
        setProgressBar(data.progressRates)
    }

    fun setProgressBar(persent: Int) {
        mPercent_layout.let {
            val cs = ConstraintSet().apply {
                clone(it)
                setHorizontalBias(R.id.layout_home_mission_progress_box, persent / 100f)
                applyTo(it)
            }
        }
        mPercent_text.text = "${persent}%"
        mPercent.progress = persent
    }
}