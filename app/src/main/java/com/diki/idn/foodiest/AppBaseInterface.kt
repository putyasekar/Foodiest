package com.diki.idn.foodiest

import android.os.Message

interface AppBaseInterface<T> {
    fun hideProgressBar()
    fun showData()
    fun showError(message: String?)
}