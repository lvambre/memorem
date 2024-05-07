package com.ltu.m7019e.memorem

import android.app.Application
import com.ltu.m7019e.memorem.database.AppContainer
import com.ltu.m7019e.memorem.database.DefaultAppContainer

class MemoremApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}