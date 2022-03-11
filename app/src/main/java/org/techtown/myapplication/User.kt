package org.techtown.myapplication

import kotlin.properties.Delegates

// 데베에 저장되는 변수값
class User() {

    lateinit var email : String
    lateinit var password : String
    lateinit var safeNum : String
    lateinit var id : String
    var measure by Delegates.notNull<Int>()
    var point by Delegates.notNull<Int>()
    lateinit var nickname : String

    constructor(email : String, password : String, safeNum : String, id : String, measure : Int, point : Int, nickname : String) : this() {
        this.email = email
        this.password = password
        this.safeNum = safeNum
        this.id = id
        this.measure = measure
        this.point = point
        this.nickname = nickname
    }
}