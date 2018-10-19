package kz.batana.lab3.home

import io.reactivex.Flowable
import io.reactivex.Observable
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.core.entity.Todo
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface HomeContract{
    interface View: IView<Presenter>{
        fun setNewsList(newsList: ArrayList<News>)
        fun msg(message: String)

    }

    interface Presenter: IPresenter<View>{
        fun getNewList()
        fun addNews(news: News)

    }

    interface Repository{
        fun getNewsList() : Flowable<List<News>>
        fun newNews(news: News): Observable<Unit>
        fun getTodos(): Observable<List<Todo>>
        fun getTodo(id: Int): Observable<Todo>
//        fun postTodo(todo: Todo): Observable<Unit>

    }
}