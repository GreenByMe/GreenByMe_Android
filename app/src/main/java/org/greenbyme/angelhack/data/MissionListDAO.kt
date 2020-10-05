package org.greenbyme.angelhack.data


data class MissionListDAO(
    val contents: List<Content>,
    val pageNumber: Int,
    val totalPage: Int
) {
    data class Content(
        val category: String,
        val dayCategory: String,
        val description: String,
        val expectCo2: Double,
        val expectTree: Double,
        val missionId: Int,
        val missionPictureUrl: String,
        val passCandidatesCount: Int,
        val subject: String,
        val missionTitle: String
    )
}
