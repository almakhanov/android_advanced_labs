package kz.batana.midterm.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.midterm.core.entity.Todo

class MainRepository(private val service: MainService,
                     private val sharedPref: SharedPref,
                     private val userDao: UserDao): MainContract.Repository {

    override fun getTodos(): Observable<ArrayList<Todo>>{
        return service.getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTodoById(index: Int): Observable<Todo> {
        return service.getTodoById(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}