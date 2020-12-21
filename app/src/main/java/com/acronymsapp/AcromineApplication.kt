package com.acronymsapp

import android.app.Activity
import android.app.Application
import com.acronymsapp.dagger.DaggerAcromineComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AcromineApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAcromineComponent.builder().
        application(this).
        build().
        inject(this)
    }
}