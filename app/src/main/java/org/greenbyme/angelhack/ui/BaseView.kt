package org.greenbyme.angelhack.ui

interface BaseView<T> {
    val presenter: T
    fun throwError(msg: Throwable)
    fun toastMessage(msg: String)
    fun getToken(): String
}