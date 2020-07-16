package org.greenbyme.angelhack.data

data class MissionTagDAO(
    val missionTagCategory: Int = -1,
    val missionTagName: String = "dummy",
    var isSelected: Boolean = false
)