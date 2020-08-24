package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.CertificationListItem

class CertificationItemViewHolder(view: View) : HomeViewHolder<CertificationListItem>(view) {
    private val imageView: ImageView = view.findViewById(R.id.iv_home_mission_image)

    override fun bind(data: CertificationListItem) {
        Glide.with(itemView.context).load(data.thumbnail).into(imageView)
    }
}
