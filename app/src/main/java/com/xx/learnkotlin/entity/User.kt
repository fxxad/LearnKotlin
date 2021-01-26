package com.xx.learnkotlin.entity

/**
 * User实体类
 */
data class User constructor(var username: String?, var password: String?, var code: String?) : Any() {
    //@JvmField 修饰的变量只会生成变量，不会生成默认的getter和setter
    constructor():this(null,null,null)
}