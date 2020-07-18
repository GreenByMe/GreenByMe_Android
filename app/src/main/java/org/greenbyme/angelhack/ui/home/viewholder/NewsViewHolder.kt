package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.NewsList

class NewsViewHolder(view: View) : HomeViewHolder<NewsList>(view) {
    private val mTitle: TextView = view.findViewById(R.id.tv_news_title)
    private val mAdapter = HomeAdapter()

    init {
        view.findViewById<ViewPager2>(R.id.vp_news_contents).run {
            adapter = mAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    override fun bind(data: NewsList) {
        mTitle.text = data.title

        mAdapter.setItems(data.newsList)
    }
}