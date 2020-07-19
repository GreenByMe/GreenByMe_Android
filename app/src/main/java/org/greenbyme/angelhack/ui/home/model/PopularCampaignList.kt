package org.greenbyme.angelhack.ui.home.model


import com.google.gson.annotations.SerializedName

data class PopularCampaignList(
    @SerializedName("content")
    var content: List<Content>,
    @SerializedName("empty")
    var empty: Boolean,
    @SerializedName("first")
    var first: Boolean,
    @SerializedName("last")
    var last: Boolean,
    @SerializedName("number")
    var number: Int,
    @SerializedName("numberOfElements")
    var numberOfElements: Int,
    @SerializedName("pageable")
    var pageable: Pageable,
    @SerializedName("size")
    var size: Int,
    @SerializedName("sort")
    var sort: SortX,
    @SerializedName("totalElements")
    var totalElements: Int,
    @SerializedName("totalPages")
    var totalPages: Int
)