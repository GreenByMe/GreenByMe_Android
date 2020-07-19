package org.greenbyme.angelhack.data

data class MyPageDAO(
    val email: String,
    val missionInfoList: List<MissionInfo>,
    val nickName: String,
    val pictureUrl: String,
    val posts: List<Post>,
    val userId: Int
) {
    data class MissionInfo(
        val finishCount: Int,
        val missionId: Int,
        val missionInfoId: Int,
        val missionInfoStatus: String,
        val progress: Int,
        val remainPeriod: Int,
        val userId: Int
    )

    data class Post(
        val nickName: String,
        val picture: String,
        val postId: Int,
        val text: String,
        val thumbsUp: Int
    )
}