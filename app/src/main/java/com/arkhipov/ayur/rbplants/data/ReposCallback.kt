package com.arkhipov.ayur.rbplants.data

interface ReposCallback<T> {
    fun onSuccess(response: T)
    fun onSuccess()
    fun onError(e: Exception)
}