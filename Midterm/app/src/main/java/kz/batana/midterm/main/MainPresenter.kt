package kz.batana.midterm.main

import android.annotation.SuppressLint
import kz.darlogistics.courier.core.util.BasePresenter

class MainPresenter(private val repository: MainContract.Repository)
    :BasePresenter<MainContract.View>(), MainContract.Presenter{


    @SuppressLint("CheckResult")
    override fun getTodos(){
        repository.getTodos()
                .subscribe(
                        {
                            getView()?.sendData(it)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }

    override fun getTodo(index: Int) {
        repository.getTodoById(index)
                .subscribe(
                        {
                            getView()?.sendDataById(it)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }

    override fun viewIsReady() {}

    override fun destroy() {}
}