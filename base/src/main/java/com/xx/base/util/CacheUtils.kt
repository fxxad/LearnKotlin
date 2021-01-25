package com.xx.base.util

import android.content.Context
import com.xx.base.BaseApplication

object CacheUtils{
    private val context = BaseApplication.curApplication()

    private val sp = context.getSharedPreferences("learnKotlin",Context.MODE_PRIVATE)

    fun save(key:String,value:String){
        sp.edit().putString(key,value).apply()
    }

    fun get(key:String):String?{
        return sp.getString(key,null)
    }


}