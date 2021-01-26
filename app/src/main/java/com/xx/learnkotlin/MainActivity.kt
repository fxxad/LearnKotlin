package com.xx.learnkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.xx.base.util.CacheUtils
import com.xx.base.util.toast
import com.xx.learnkotlin.entity.User
import com.xx.learnkotlin.widget.CodeView
import com.xx.lesson.LessonActivity

fun Activity.log(text:String){
    Log.e("Activity",text)
}

fun Context.log(text:String){
    Log.e("Context",text)
}

class MainActivity : AppCompatActivity(),View.OnClickListener{

    private val userNameKey = "username"
    private val passwordKey = "password"

    var etUserName:EditText ?= null
    //暗示这个变量使用前是要初始化的,不可空变量延迟初始化
    lateinit var etPassword:EditText
    lateinit var etCode:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log("activity")
        (this as Context).log("context")

        etUserName = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)
        //Button!是平台类型，IDE自动提供的 不能自己编辑
        val btLogin = findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener(this)

        val codeView = findViewById<CodeView>(R.id.cv)
        codeView.setOnClickListener(this)

        etUserName?.setText(CacheUtils.get(userNameKey))
        etPassword.setText(CacheUtils.get(passwordKey))
    }

    override fun onClick(view: View?) {
        if (view is Button){
            //此处kotlin不需要进行再次强制类型转换了，因为上面的if分支已经判断过了类型
//            val button = view as Button
            login()
        }else if(view is CodeView){
            view.updateCode()
        }
    }

    private fun login(){
        val userName = etUserName?.text.toString()
        val password = etPassword.text.toString()
        val code = etCode.text.toString()
        val user = User(userName, password, code)
        if(verify(user)){
            CacheUtils.save(userNameKey,userName)
            CacheUtils.save(passwordKey,password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User):Boolean{
        if(user.username?.length ?: 0 <4){
            toast("用户名不合法")
            return false
        }
        if(user.password?.length ?: 0 <4){
            toast("密码不合法")
            return false
        }
        return true

//        if(user.username == null || user.username!!.length < 4){
//            toast("用户名不合法")
//            return false
//        }
//        if(user.password == null || user.password!!.length < 4){
//            toast("密码不合法")
//            return false
//        }
//        return true
    }
}