package org.greenbyme.angelhack.ui.mypage.post

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object PostBinding {
    @JvmStatic
    @BindingAdapter("bind:src")
    fun bind(view: ImageView, src: String?) {
        Glide.with(view.context).load(src).into(view)
    }
}