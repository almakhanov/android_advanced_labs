package kz.batana.lab3.home.news


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_news.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.Constants.NEWS
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.home.HomeContract
import kz.batana.lab3.home.news_form.NewsFormActivity
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

        fab_add_news.setOnClickListener{ fab ->
//            Snackbar.make(fab, "News is created!", Snackbar.LENGTH_SHORT)
//                    .show()
//
//            presenter.addNews()

            startActivityForResult(Intent(activity, NewsFormActivity::class.java), 1)
        }

    }

    override fun msg(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setNewsList(newsList: ArrayList<News>) {
        if (context?.resources?.configuration?.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            recycler_news.layoutManager = GridLayoutManager(context,2)
        } else{
            recycler_news.layoutManager = LinearLayoutManager(context)
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                val news = data?.getSerializableExtra(Constants.NEWS) as News
                presenter.addNews(news)
            }
        }
    }





}
