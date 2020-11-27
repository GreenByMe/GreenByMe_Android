package org.greenbyme.angelhack.data


import com.google.gson.annotations.SerializedName

data class NaverUserDAO(
    @SerializedName("message")
    var message: String,
    @SerializedName("response")
    var response: Response,
    @SerializedName("resultcode")
    var resultcode: String
) {
    data class Response(
        @SerializedName("id")
        var id: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("email")
        var email: String
    )
}