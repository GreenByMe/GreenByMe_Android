package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.NewsListItem

class NewsListItemViewHolder(view: View) : HomeViewHolder<NewsListItem>(view) {

    private val mPicasso = Picasso.get()
    private val newsImg: ImageView = view.findViewById(R.id.iv_news_contents)

    override fun bind(data: NewsListItem) {
        mPicasso.load(data.thumbnail).into(newsImg)
    }
}