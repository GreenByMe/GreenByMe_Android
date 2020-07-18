package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.TextView
import org.greenbyme.angelhack.ui.home.model.Header
import org.greenbyme.angelhack.R

class HeaderViewHolder(view: View) : HomeViewHolder<Header>(view) {
    private val mUserName : TextView  = view.findViewById(R.id.tv_home_name)

    override fun bind(data: Header) {
        mUserName.text = data.userName
    }
}