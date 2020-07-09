package com.example.ischeduler

import android.app.Application
import io.realm.Realm

class ISchedulerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}