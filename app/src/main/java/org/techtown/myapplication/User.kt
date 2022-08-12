package org.techtown.myapplication

import com.google.firebase.database.PropertyName
import kotlin.properties.Delegates

// 데베에 저장되는 변수값
class User() {

    @PropertyName("email")
    lateinit var email : String
    lateinit var password : String
    @PropertyName("safeNum")
    var safeNum : String ?= null
    lateinit var id : String
    var measure by Delegates.notNull<Int>()
    var point by Delegates.notNull<Int>()
    lateinit var nickname : String
    @PropertyName("elec")
    lateinit var elec : String
    lateinit var address : String
    lateinit var zipcode : String

    constructor(email : String, password : String, safeNum : String, id : String, measure : Int, point : Int, nickname : String, elec : String, address : String, zipcode : String) : this() {
        this.email = email
        this.password = password
        this.safeNum = safeNum
        this.id = id
        this.measure = measure
        this.point = point
        this.nickname = nickname
        this.elec = elec
        this.address = address
        this.zipcode = zipcode
    }
}