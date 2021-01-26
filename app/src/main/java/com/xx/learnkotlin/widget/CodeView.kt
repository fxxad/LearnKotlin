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

class CodeView @JvmOverloads constructor(context: Context,attr: AttributeSet? = null): AppCompatTextView (context,attr){

    private val codeList = arrayOf("kotlin","android","java","https","okhttp","tcp/ip")

    private val mPaint = Paint().apply {
        this.isAntiAlias = true
        this.style = Paint.Style.STROKE
        this.color = Color.RED
        this.strokeWidth = 6f.dp2px()
    }

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        gravity = Gravity.CENTER
        setBackgroundColor(Color.GREEN)
        setTextColor(Color.WHITE)
        updateCode()
    }

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