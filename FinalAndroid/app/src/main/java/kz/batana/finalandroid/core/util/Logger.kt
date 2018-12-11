package kz.batana.finalandroid.core.util

import android.util.Log

/**
 * Handle Logging in app
 */
object Logger {

    fun msg(tag: String, msg: Any?) {
        Log.i(tag, "$msg")
    }

    fun msg(msg: Any?) {
        msg("MSG", "$msg")
    }

    fun api(msg: String?) {
        msg("API", "$msg")
    }
}