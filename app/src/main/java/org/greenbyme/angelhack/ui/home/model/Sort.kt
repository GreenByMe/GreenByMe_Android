package org.greenbyme.angelhack.ui.home.model


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("empty")
    var empty: Boolean,
    @SerializedName("sorted")
    var sorted: Boolean,
    @SerializedName("unsorted")
    var unsorted: Boolean
)