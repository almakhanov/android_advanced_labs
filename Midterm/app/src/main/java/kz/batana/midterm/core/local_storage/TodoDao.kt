package kz.batana.lab3.home.news

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.lab3.core.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE done = 0 AND user_email=:user")
    fun getTodos(user: String): Flowable<List<Todo>>

    @Query("SELECT * FROM todo WHERE done != 0 AND user_email=:user")
    fun getTodosDone(user: String): Flowable<List<Todo>>

    @Query("UPDATE todo SET done = 1 WHERE id=:id")
    fun doneTodo(id: Int)


}