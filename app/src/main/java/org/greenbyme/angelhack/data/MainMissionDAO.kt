package org.greenbyme.angelhack.data

data class MainMissionDAO(
    val contents: List<Content>,
    val pageNumber: Int,
    val totalPage: Int
) {
    data class Content(
        val category: String,
        val dayCategory: String,
        val description: String,
        val endDate: String,
        val id: Int,
        val passCandidates: Int,
        val pictureUrl: String,
        val startDate: String,
        val subject: String,
        val title: String
    )
}