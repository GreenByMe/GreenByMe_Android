package org.greenbyme.angelhack.ui.certification.model

data class CertificationItems(
    val category: String,
    val certifiaciontTest: String,
    val current: Int,
    val endDate: String,
    val finishCount: Int,
    val personalMissionId: Int,
    val pictureUrl: String,
    val progressRates: Int,
    val startDate: String,
    val subject: String
)