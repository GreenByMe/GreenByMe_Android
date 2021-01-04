package org.greenbyme.angelhack.ui.login.signin

import com.google.gson.JsonObject

class SignUpDTO(
    val email: String,
    val password: String,
    val name: String = "",
    val phone: String = "",
    val nickname: String = "",
    val marketingTerms: Boolean = true
) {
    fun toJSON(): JsonObject {
        val json = JsonObject()
        json.addProperty("email", email)
        json.addProperty("name", nickname)
        json.addProperty("nickname", nickname)
        json.addProperty("password", password)
        json.addProperty("phone", phone)
        json.addProperty("marketingTerms", marketingTerms)
        return json
    }
}