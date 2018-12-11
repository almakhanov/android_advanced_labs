package kz.batana.finalandroid

import android.app.Application
import kz.batana.finalandroid.core.util.Logger
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