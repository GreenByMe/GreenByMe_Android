package org.greenbyme.angelhack.ui

import android.util.Log

interface BaseView<T>{
    val presenter:T
    fun throwError(msg: Throwable)
    fun toastMessage(msg: String)
    fun getToken():String
}