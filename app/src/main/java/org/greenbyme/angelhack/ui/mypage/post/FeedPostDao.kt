package org.greenbyme.angelhack.ui.mypage.post

data class FeedPostDao(
    val createDate: String,
    val lastModifiedDate: String,
    val nickName: String,
    val picture: String,
    val postId: Int,
    val text: String,
    val thumbsUp: Int,
    val title: String
)