package com.example.ischeduler

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class gakka :RealmObject() {
    @PrimaryKey
    var id: Long=0
    var gakka: String=""
    var year: String=""

}