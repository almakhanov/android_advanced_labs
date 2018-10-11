package kz.batana.midterm.main

import io.reactivex.Observable
import kz.batana.midterm.core.entity.Todo
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface MainContract {

    interface View: IView<Presenter>{
        fun sendData(todoList: ArrayList<Todo>)
        fun msg(msg: String)
        fun sendDataById(todo: Todo)
    }

    interface Presenter: IPresenter<View> {
        fun getTodos()
        fun getTodo(index: Int)
    }

    interface Repository{
        fun getTodos(): Observable<ArrayList<Todo>>
        fun getTodoById(index: Int): Observable<Todo>
    }
}