package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.text.parseAsHtml
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.User

class HeaderViewHolder(view: View) : HomeViewHolder<User>(view) {
    private val mUserName: TextView = view.findViewById(R.id.tv_home_name)
    private val mSentence: TextView = view.findViewById(R.id.tv_home_desc)
    private val mCampaign: TextView = view.findViewById(R.id.campaign_count_title)
    private val mCo2: TextView = view.findViewById(R.id.co_title)
    private val mTree: TextView = view.findViewById(R.id.tree_count_title)
    private var mPercent: Int = 0

    override fun bind(data: User) {
        mUserName.text = "안녕하세요 " + data.nickName + "님"
        mSentence.text = data.desc.parseAsHtml()
        mCampaign.text = data.progressCampaign.toString() + "개"
        mCo2.text = data.co2.toString() + "Kg"
        mTree.text = data.tree.toString() + "그루"
        mPercent = data.rate

    }
}