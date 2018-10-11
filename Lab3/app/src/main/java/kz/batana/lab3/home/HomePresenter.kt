package kz.batana.lab3.home

import android.annotation.SuppressLint
import kz.batana.lab3.core.entity.News
import kz.darlogistics.courier.core.util.BasePresenter

class HomePresenter(private val repository: HomeContract.Repository)
    : HomeContract.Presenter, BasePresenter<HomeContract.View>(){

    @SuppressLint("CheckResult")
    override fun getNewList() {
        repository.getNewsList().subscribe(
                {
                    getView()?.setNewsList(it as ArrayList<News>)
                },
                {
                    getView()?.msg(it.message!!)
                })



    }

    @SuppressLint("CheckResult")
    override fun addNews(news: News) {
        repository.newNews(news)
                .subscribe(
                        {
                            getNewList()
                        },
                        {
                            getView()?.msg(it.message!!)
                        })
    }


    override fun viewIsReady() {}

    override fun destroy() {}

}