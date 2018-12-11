package kz.batana.finalandroid.core.util

/**
 * Common Presenter interface for app
 */
interface IPresenter<V> {

    fun attachView(view: V)

    fun viewIsReady()

    fun detachView()

    fun destroy()
}