/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 14/08/2018.
 */

package kz.batana.midterm

import android.app.Application
import kz.batana.khanproject.Logger
import kz.batana.lab3.appModules
import org.koin.android.ext.android.startKoin

/**
 * Custom application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.msg("accepted", "App")

        startKoin(this, appModules)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set

    }
}