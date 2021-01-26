package com.xx.base.util

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.xx.base.BaseApplication

/***
 * 工具类
 */


private val displayMetrics = Resources.getSystem().displayMetrics

/**
 * dp转px
 * 扩展函数   格式： 接收者.函数名
 */
fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

/**
 * Toast
 * @JvmOverloads 会针对java生成重载函数
 */
@JvmOverloads
fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(BaseApplication.curApplication, msg, duration).show()