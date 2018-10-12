package kz.batana.midterm.main

import io.reactivex.Flowable
import kz.batana.lab3.core.entity.Todo
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface MainContract {

    interface View: IView<Presenter>{
        fun sendTodos(todoList: ArrayList<Todo>)
        fun msg(msg: String)
        fun sendTodosDone(todoList: ArrayList<Todo>)
    }

    interface Presenter: IPresenter<View> {
        fun getTodos()
        fun addTodo(todo: Todo)
        fun getDones()
    }

    interface Repository{
        fun addTodo(todo: Todo): Flowable<Unit>
        fun doneTodo(todo: Todo): Flowable<Unit>
        fun getTodos(): Flowable<List<Todo>>
        fun getTodosDone(): Flowable<List<Todo>>
    }

    interface TodoListener{
        fun getTodos()
        fun addTodo(todo: Todo)

    }

    interface TodoDoneListener{
        fun getTodosDone()

    }
}