package org.techtown.myapplication

import kotlin.properties.Delegates

// 데베에 저장되는 변수값
class User() {

    lateinit var email : String
    lateinit var password : String
    lateinit var safeNum : String
    lateinit var id : String
    var measure by Delegates.notNull<Int>()

    constructor(email : String, password : String, safeNum : String, id : String, measure : Int) : this() {
        this.email = email
        this.password = password
        this.safeNum = safeNum
        this.id = id
        this.measure = measure
    }
}