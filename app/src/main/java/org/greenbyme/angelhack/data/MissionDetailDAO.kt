package org.greenbyme.angelhack.data

data class MissionDetailDAO(
    val category: String,
    val dayCategory: String,
    val description: String,
    val endDate: String,
    val expectCo2: Double,
    val expectTree: Double,
    val id: Int,
    val pictureUrl:String,
    val missionCertificationMethod: MissionCertificationMethod,
    val passCandidatesCount: Int,
    val progressByMissionId: Int,
    val startDate: String,
    val subject: String
) {
    data class MissionCertificationMethod(
        val missionCertificateCount: String,
        val text: String,
        val title: String
    )
}