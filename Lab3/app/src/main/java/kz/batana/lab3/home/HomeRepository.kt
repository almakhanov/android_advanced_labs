package kz.batana.lab3.home

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.core.entity.Post
import kz.batana.lab3.core.entity.Todo
import kz.batana.lab3.home.news.NewsDao
import kz.batana.lab3.home.todo.TodoService

class HomeRepository(private val newsDao: NewsDao,
                     private val service: TodoService): HomeContract.Repository{

    override fun getTodos(): Observable<List<Todo>> {
        return service.getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTodo(id: Int): Observable<Todo> {
        return service.getTodo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun setPost(post: Post): Observable<Post>{
        return service.setPost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNewsList(): Flowable<List<News>> {
        return newsDao.getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun newNews(news: News): Observable<Unit> {
        return Observable.just(newsDao.insertNews(news))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}