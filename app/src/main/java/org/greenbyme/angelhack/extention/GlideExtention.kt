package org.greenbyme.angelhack.extention

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import org.greenbyme.angelhack.network.ApiService

fun RequestManager.loadUriWithToken(uri: String): RequestBuilder<Drawable> {
    return (this.load(
        GlideUrl(
            uri.replace("iptime.org","greenbyme.shop"), LazyHeaders.Builder()
                .addHeader("jwt", ApiService.token)
                .build()
        )
    ))
}
