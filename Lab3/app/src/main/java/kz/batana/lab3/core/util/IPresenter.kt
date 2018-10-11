/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 14/08/2018.
 */

package kz.darlogistics.courier.core.util

/**
 * Common Presenter interface for app
 */
interface IPresenter<V> {

    fun attachView(view: V)

    fun viewIsReady()

    fun detachView()

    fun destroy()
}