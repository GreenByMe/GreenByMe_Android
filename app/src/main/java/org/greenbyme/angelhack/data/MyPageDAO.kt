package org.greenbyme.angelhack.data

import com.google.gson.annotations.SerializedName

data class MyPageDAO(
    @SerializedName("email")
    val email: String,
    val expectCo2: Int,
    val expectTree: Int,
    val personalMissions: List<MissionInfo>,
    val nickName: String,
    val passMissionCount: Int,
    val pictureUrl: String,
    val posts: List<Post>,
    val userId: Int
) {
    data class MissionInfo(
        val endDate: String,
        val finishCount: Int,
        val missionId: Int,
        val missionInfoId: Int,
        val missionInfoStatus: String,
        val missionPictureUrl: String,
        val progress: Int,
        val remainPeriod: RemainPeriod,
        val startDate: String,
        val userId: Int
    ) {
        data class RemainPeriod(
            val remainDay: Int,
            val remainHour: Int,
            val remainMin: Int
        )
    }

    data class Post(
        val createDate: String,
        val lastModifiedDate: String,
        val nickName: String,
        val picture: String,
        val postId: Int,
        val text: String,
        val thumbsUp: Int,
        val title: String
    )
}