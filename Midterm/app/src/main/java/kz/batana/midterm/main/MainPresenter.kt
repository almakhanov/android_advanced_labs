package kz.batana.midterm.main

import android.annotation.SuppressLint
import kz.batana.lab3.core.entity.Todo
import kz.darlogistics.courier.core.util.BasePresenter

class MainPresenter(private val repository: MainContract.Repository)
    :BasePresenter<MainContract.View>(), MainContract.Presenter{


    @SuppressLint("CheckResult")
    override fun getTodos(){
        repository.getTodos()
                .subscribe(
                        {
                            getView()?.sendTodos(it as ArrayList<Todo>)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }

    @SuppressLint("CheckResult")
    override fun getDones() {
        repository.getTodosDone()
                .subscribe(
                        {
                            getView()?.sendTodosDone(it as ArrayList<Todo>)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })    }

    @SuppressLint("CheckResult")
    fun getTodosDone() {
        repository.getTodosDone()
                .subscribe(
                        {
                            getView()?.sendTodosDone(it as ArrayList<Todo>)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }

    @SuppressLint("CheckResult")
    override fun addTodo(todo: Todo) {
        repository.addTodo(todo).subscribe(
                {
                    getTodos()
                },
                {
                    getView()?.msg(it.message!!)
                })
    }

    override fun viewIsReady() {}

    override fun destroy() {}
}