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
 */
fun dp2px(dp:Float):Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, displayMetrics)
}

/**
 * Toast
 */
fun toast(msg:String){
    toast(msg,Toast.LENGTH_SHORT)
}

fun toast(msg: String,duration:Int){
    Toast.makeText(BaseApplication.curApplication(),msg,duration).show()
}