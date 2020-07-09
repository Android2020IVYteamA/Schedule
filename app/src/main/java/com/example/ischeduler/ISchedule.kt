package com.example.ischeduler

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ISchedule :RealmObject() {
  @PrimaryKey
  var id: Long=0
    var title: String=""
    var detail: String=""
    var date: String =""
    var time: String=""
    var place: String=""


}