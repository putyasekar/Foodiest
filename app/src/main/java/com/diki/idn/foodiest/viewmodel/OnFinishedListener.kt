package com.diki.idn.foodiest.viewmodel

interface OnFinishedListener<T> {
    fun success(data: T)
    fun failure(message: String?)
}
