package com.xx.base.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {
    private val gson = Gson()

    fun <T> convert(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }


    fun <T> get(path: String, type: Type, callback: EntityCallback<T>) {
        val request = Request.Builder()
            .url("https:api.hencoder.com/$path")
            .build()
        val call = this.newCall(request)
        call.enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..299 -> callback.onSuccess(convert(response.body()!!.string(), type))
                    in 400..499 -> callback.onFailure("客户端错误")
                    in 500..599 -> callback.onFailure("服务器错误")
                    else -> callback.onFailure("未知错误")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("网络异常")
            }

        })
    }

}