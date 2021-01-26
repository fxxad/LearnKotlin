package com.xx.base

import android.app.Application
import android.content.Context

/**
 * 基础Application类，提供Application上下文
 */
class BaseApplication : Application() {

    //伴生对象
    companion object{
        lateinit var curApplication: Application
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        curApplication = this
    }
}