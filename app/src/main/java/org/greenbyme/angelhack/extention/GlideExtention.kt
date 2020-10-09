package org.greenbyme.angelhack.extention

import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders


fun String.parseGlideUri(token: String): GlideUrl {
    val uri = this
    Log.d("glideyes", uri)
    return GlideUrl(
        uri, LazyHeaders.Builder()
            .addHeader("jwt", token)
            .build()
    )
}

fun RequestManager.load(uri : String,token: String): RequestBuilder<Drawable> {
    return (this.load(GlideUrl(
        uri, LazyHeaders.Builder()
            .addHeader("jwt", token)
            .build()
    )))
}
