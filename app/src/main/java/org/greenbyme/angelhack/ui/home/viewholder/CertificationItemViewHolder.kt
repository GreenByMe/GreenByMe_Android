package org.greenbyme.angelhack.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.ui.home.model.CertificationListItem

class CertificationItemViewHolder(view: View) : HomeViewHolder<CertificationListItem>(view) {
    private val picasso = Picasso.get()
    private val imageView: ImageView = view.findViewById(R.id.iv_home_mission_image)

    override fun bind(data: CertificationListItem) {
        picasso.load(data.thumbnail).into(imageView)
    }
}
