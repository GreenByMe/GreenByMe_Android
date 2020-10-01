package org.greenbyme.angelhack.ui.mypage

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.data.MyPageDAO
import org.greenbyme.angelhack.ui.home.adapter.HomeAdapter
import org.greenbyme.angelhack.ui.home.model.CertificationList
import org.greenbyme.angelhack.ui.home.model.CertificationListItem

object MyPageBinding {
    @JvmStatic
    @BindingAdapter("bind:item")
    fun bindItem(
        recyclerView: RecyclerView,
        items: List<MyPageDAO.Post>?
    ) {
        CertificationList(
            certificationList = items?.map {
                CertificationListItem(it.postId, it.picture)
            } ?: ArrayList<CertificationListItem>(),
            isVisibleTitle = false,
            title = ""
        ).toList().let {
            (recyclerView.adapter as HomeAdapter).setItems(it)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:item")
    fun bindSrc(imageView : de.hdodenhof.circleimageview.CircleImageView, url : String ){
        Glide.with(imageView.context).load(url).into(imageView)
    }

}