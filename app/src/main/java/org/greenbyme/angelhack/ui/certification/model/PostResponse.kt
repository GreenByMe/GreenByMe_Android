package org.greenbyme.angelhack.ui.certification.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("userId")
    var userId: Int
)