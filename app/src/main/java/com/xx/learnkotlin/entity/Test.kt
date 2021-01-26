package com.xx.learnkotlin.entity

import okhttp3.Request
import okhttp3.Response

fun main() {
    var user = User("fxx","123","2345")
    var userCopy = user.copy()

    println(user)
    println(userCopy)

    //kotlin中 == 是调用equals方法
    println(user == userCopy)

    // 比较对象地址可以使用 ===
    println(user === userCopy)


    repeat(100){
        println(it)
    }

    println("haha1")

    log("haha2")

}

inline fun log(text:String){
    println(text)
}