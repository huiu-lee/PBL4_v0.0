package org.techtown.myapplication

// 데베에 저장되는 변수값
class User() {

    lateinit var email : String
    lateinit var password : String
    lateinit var safeNum : String
    lateinit var id : String

    constructor(email : String, password : String, safeNum : String, id : String) : this() {
        this.email = email
        this.password = password
        this.safeNum = safeNum
        this.id = id
    }
}