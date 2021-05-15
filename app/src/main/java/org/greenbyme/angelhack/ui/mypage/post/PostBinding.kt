package org.greenbyme.angelhack.ui.mypage.post

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.greenbyme.angelhack.extention.loadUriWithToken

object PostBinding {
    @JvmStatic
    @BindingAdapter("bind:src")
    fun bind(view: ImageView, src: String?) {
        if (src != null) {
            Glide.with(view.context).loadUriWithToken(src).into(view)
        }
    }
}