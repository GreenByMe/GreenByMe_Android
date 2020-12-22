package org.greenbyme.angelhack.ui.mission.detail

data class UserFeedPostDAO(
    val contents: List<Contents>
) {
    data class Contents(
        val nickName: String,
        val picture: String,
        val postId: Int,
        val thumbsUp: Int
    )
}
