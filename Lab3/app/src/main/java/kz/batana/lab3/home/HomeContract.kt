package kz.batana.lab3.home

import kz.batana.lab3.home.entity.News
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface HomeContract{
    interface View: IView<Presenter>{
        fun setNewsList(newsList: ArrayList<News>)

    }

    interface Presenter: IPresenter<View>{
        fun getNewList()

    }

    interface Repository{
        fun getNewsList() : ArrayList<News>

    }
}