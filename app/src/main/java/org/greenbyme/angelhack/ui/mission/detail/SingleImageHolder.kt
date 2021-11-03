package org.greenbyme.angelhack.ui.mission.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.extention.loadUriWithToken
import org.greenbyme.angelhack.ui.BaseAdapter

class SingleImageHolder(view: View) : BaseAdapter.BaseHolder<UserFeedPostDAO.Contents>(view) {
    override fun getItemResId(): Int {
        return R.layout.item_user_feed_img
    }

    override fun bind(items: UserFeedPostDAO.Contents, onClickListener: BaseAdapter.OnClickPositionListener?) {
        with(itemView) {
            Glide.with(context).loadUriWithToken(items.picture).into(
                findViewById(R.id.img_user_feed_photo)
            )
        }
    }

    override fun from(parent: ViewGroup, onClickListener: BaseAdapter.OnClickPositionListener?): RecyclerView.ViewHolder {
        return SingleImageHolder(getInflater(parent)).apply {
            itemView.setOnClickListener {
                onClickListener?.onClick(itemView, adapterPosition)
            }
        }
    }

}