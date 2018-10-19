package kz.batana.lab3.home.todo

import android.annotation.SuppressLint
import kz.batana.lab3.core.entity.Todo
import kz.batana.lab3.home.HomeContract
import kz.darlogistics.courier.core.util.BasePresenter

class TodoPresenter(private val repository: HomeContract.Repository)
    : BasePresenter<TodoContract.View>(), TodoContract.Presenter{

    @SuppressLint("CheckResult")
    override fun getTodos() {
        repository.getTodos()
                .subscribe(
                        {
                            getView()?.setTodoList(it as ArrayList<Todo>)
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }

//    @SuppressLint("CheckResult")
//    override fun addTodo(todo: Todo) {
//        repository.postTodo(todo)
//                .subscribe(
//                        {
//                            getView()?.msg("posted!")
//                        },
//                        {
//                            getView()?.msg(it.message!!)
//                        })
//    }

    override fun viewIsReady() {}

    override fun destroy() {}
}