package kz.batana.midterm.main

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.lab3.core.entity.Todo
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.lab3.home.news.TodoDao

class MainRepository(private val service: MainService,
                     private val sharedPref: SharedPref,
                     private val userDao: TodoDao): MainContract.Repository {

    override fun doneTodo(todo: Todo) : Flowable<Unit> {
        return Flowable.just(userDao.doneTodo(todo.id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTodos(): Flowable<List<Todo>> {
        return userDao.getTodos(sharedPref.getUserEmail()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTodosDone(): Flowable<List<Todo>>{
        return userDao.getTodosDone(sharedPref.getUserEmail()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addTodo(todo: Todo): Flowable<Unit> {
        return Flowable.just(userDao.insertTodo(todo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}