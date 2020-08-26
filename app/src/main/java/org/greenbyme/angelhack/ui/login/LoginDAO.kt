package org.greenbyme.angelhack.ui.login

import com.google.gson.annotations.SerializedName

data class LoginDAO(
        @SerializedName("status")
        val status:String,
        @SerializedName("data")
        val data :String,
        @SerializedName("message")
        val message:String
)