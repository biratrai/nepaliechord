package com.nepali.echord

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class ChordApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return MainApplicationComponent.Builder
    }


//    val firebase: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate() {
        super.onCreate()
    }

}