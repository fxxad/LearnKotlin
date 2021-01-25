package com.xx.base.http

interface EntityCallback<T> {
    fun onFailure(errorMsg:String)
    fun onSuccess(data:T)
}