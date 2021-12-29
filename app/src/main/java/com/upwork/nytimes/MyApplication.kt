package com.upwork.nytimes

import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.multidex.MultiDex


class MyApplication : Application() {

    lateinit var mContext: Context

    companion object {
        private lateinit var mInstance: MyApplication

        @Synchronized
        fun getInstance(): MyApplication {
            return mInstance
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = this

    }

}