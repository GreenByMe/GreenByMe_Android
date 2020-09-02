package org.greenbyme.angelhack.ui.home.model

data class ResponseBase<T>(
    var status: String,
    var data: T,
    var message: String
)