package kz.batana.lab3.home

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.home.news.NewsDao

class HomeRepository(private val newsDao: NewsDao): HomeContract.Repository{
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