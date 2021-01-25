package com.xx.learnkotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.xx.base.util.dp2px
import java.util.*

class CodeView: AppCompatTextView {

    constructor(context:Context):this(context,null){
    }

    constructor(context: Context,attr:AttributeSet?):super(context,attr){
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        gravity = Gravity.CENTER
        setBackgroundColor(Color.GREEN)
        setTextColor(Color.WHITE)

        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        mPaint.strokeWidth = dp2px(12f)

        updateCode()
    }

    private val codeList = arrayOf("kotlin","android","java","https","okhttp","tcp/ip")

    private val mPaint = Paint()


    fun updateCode(){
        val random = Random().nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(),0f,mPaint)
        super.onDraw(canvas)
    }

}