package kz.batana.lab3.home

import kz.darlogistics.courier.core.util.BasePresenter

class HomePresenter(private val repository: HomeContract.Repository)
    : HomeContract.Presenter, BasePresenter<HomeContract.View>(){

    override fun viewIsReady() {}
    override fun getNewList() {
        getView()?.setNewsList(repository.getNewsList())
    }

    override fun destroy() {}

}