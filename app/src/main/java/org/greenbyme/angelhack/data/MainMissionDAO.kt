package org.greenbyme.angelhack.data

data class MainMissionDAO(
    val contents: List<Content> = ArrayList<Content>(),
    val pageNumber: Int = 0,
    val totalPage: Int = 0
) {
    data class Content(
        val category: String = "",
        val dayCategory: String = "",
        val description: String = "",
        val endDate: String = "",
        val id: Int = 0,
        val passCandidates: Int = 0,
        val missionPictureUrl: String = "",
        val startDate: String = "",
        val subject: String = "",
        val title: String = ""
    )
}