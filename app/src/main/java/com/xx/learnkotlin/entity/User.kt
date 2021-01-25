package com.xx.learnkotlin.entity

class User:Any{
    //@JvmField 只会生成变量，不会生成默认的getter和setter

    var userName:String?=null
    var password:String?=null
    var code:String ?= null
        set(value) {
            field = value
        }
        get() = field


    constructor(){

    }

    constructor(username:String?,password:String?,code:String?){
        this.userName = username
        this.password = password
        this.code = code
    }
}