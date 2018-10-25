package kz.batana.lab3.home.todo

import android.annotation.SuppressLint
import kz.batana.lab3.core.entity.Post
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

    @SuppressLint("CheckResult")
    override fun setPost(post: Post){
        repository.setPost(post)
                .subscribe(
                        {
                            getView()?.msg(it.toString())
                        },
                        {
                            getView()?.msg(it.message!!)
                        }
                )
    }

    override fun viewIsReady() {}

    override fun destroy() {}
}