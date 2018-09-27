package kz.batana.lab3.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants.NEWS
import kz.batana.lab3.home.entity.News
import kz.batana.lab3.news_details.NewsDetailsActivity
import org.koin.android.ext.android.inject
import java.io.Serializable

class NewsFragment : Fragment(), HomeContract.View, NewsAdapter.NewsItemClicked {

    override val presenter: HomeContract.Presenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getNewList()
    }

    override fun setNewsList(newsList: ArrayList<News>) {
        recycler_news.layoutManager = LinearLayoutManager(activity)
        recycler_news.adapter = NewsAdapter(activity!!, newsList, this)
    }

    override fun onItemClicked(news: News) {
        val intent = Intent(activity, NewsDetailsActivity::class.java)
        intent.putExtra(NEWS, news as Serializable)
        startActivity(intent)
        Logger.msg("accepted", "clicked -> $news")
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }


}
