package kz.batana.lab3.home.todo

import kz.batana.lab3.core.entity.Todo
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface TodoContract {

    interface View: IView<Presenter>{
        fun setTodoList(todoList: ArrayList<Todo>)
        fun msg(message: String)
    }

    interface Presenter: IPresenter<View>{
        fun getTodos()
//        fun addTodo(todo: Todo)
    }

}