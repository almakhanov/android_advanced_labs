package kz.batana.lab3.home.todo

import io.reactivex.Observable
import kz.batana.lab3.core.entity.Post
import kz.batana.lab3.core.entity.Todo
import retrofit2.http.*

interface TodoService {

    @GET("todos")
    fun getTodos() : Observable<List<Todo>>

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: Int) : Observable<Todo>

    @POST("posts")
    @Headers("Content-Type: application/json")
    fun setPost(@Body post: Post) : Observable<Post>
}