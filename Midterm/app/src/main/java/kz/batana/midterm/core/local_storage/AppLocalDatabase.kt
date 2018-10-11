package kz.batana.lab3.core.local_storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.core.entity.User
import kz.batana.lab3.home.news.NewsDao

@Database(entities = [User::class, News::class], version = 1)
abstract class AppLocalDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao

    abstract fun newsDao() : NewsDao

}