package org.greenbyme.angelhack.ui

data class temp(
    val endDate: String,
    val finishCount: Int,
    val manyPeople: Int,
    val missionId: Int,
    val missionTitle: String,
    val personalMissionId: Int,
    val pictureUrl: String,
    val progress: Int,
    val remainPeriod: RemainPeriod,
    val startDate: String,
    val status: String
) {
    data class RemainPeriod(
        val remainDay: Int,
        val remainHour: Int,
        val remainMin: Int
    )
}