package org.greenbyme.angelhack.data

data class PersonalMissionDAO(
    val contents: List<Content>,
    val pageNumber: Int,
    val totalPage: Int
) {
    data class Content(
        val endDate: String,
        val finishCount: Int,
        val missionId: Int,
        val missionPictureUrl: String,
        val personalMissionStatus: String,
        val personalMission_id: Int,
        val progress: Int,
        val remainPeriod: RemainPeriod,
        val startDate: String
    ) {
        data class RemainPeriod(
            val remainDay: Int,
            val remainHour: Int,
            val remainMin: Int
        )
    }
}
