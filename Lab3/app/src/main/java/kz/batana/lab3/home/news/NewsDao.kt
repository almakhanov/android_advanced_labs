package kz.batana.lab3.home.news

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.lab3.core.entity.News

@Dao
interface NewsDao {
    @Insert
    fun insertNews(news: News)

    @Query("SELECT * FROM news")
    fun getAllNews(): Flowable<List<News>>

    @Query("DELETE FROM news")
    fun delete()
}