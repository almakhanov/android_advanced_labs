package kz.batana.lab3.news_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_details.*
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants.NEWS
import kz.batana.lab3.home.entity.News

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val news = intent.getSerializableExtra(NEWS) as News
        if (news.imageUrl != "")
            Glide.with(this).load(news.imageUrl).into(main_backdrop)
        tvTitle.text = news.title
        tvDate.text = news.date
        tvContent.text = news.content

    }
}
