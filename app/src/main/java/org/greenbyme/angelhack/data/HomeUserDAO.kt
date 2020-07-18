package org.greenbyme.angelhack.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("nickName")
    var nickName: String = "",
    @SerializedName("email")
    var email: String = ""

//    @SerializedName("posts")
//    var posts: Int = 0
//    @SerializedName("missionInfoList")
//var missionInfoList: List<Mission> = "",
)