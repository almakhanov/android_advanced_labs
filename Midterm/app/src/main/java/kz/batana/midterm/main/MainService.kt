package kz.batana.midterm.main

import io.reactivex.Observable
import kz.batana.midterm.core.entity.Todo
import retrofit2.http.GET
import retrofit2.http.Path


interface MainService {

    @GET("todos/")
    fun getTodos(): Observable<ArrayList<Todo>>

    @GET("todos/{id}")
    fun getTodoById(@Path("id") id: Int): Observable<Todo>

}