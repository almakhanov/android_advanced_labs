package kz.batana.lab3.core.local_storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.entity.News
import kz.batana.lab3.core.entity.User
import kz.batana.lab3.home.news.NewsDao

@Database(entities = [User::class, News::class], version = 1)
abstract class AppLocalDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun newsDao() : NewsDao

    companion object {
        var INSTANCE: AppLocalDatabase? = null
        fun getInstance(context: Context): AppLocalDatabase? {
            INSTANCE ?: synchronized(AppLocalDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppLocalDatabase::class.java, "database")
                        .build()
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}