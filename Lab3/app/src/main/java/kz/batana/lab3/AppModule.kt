package kz.batana.lab3

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.auth.authModule
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.local_storage.AppLocalDatabase
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.lab3.core.local_storage.SharedPrefImpl
import kz.batana.lab3.home.homeModule
import kz.batana.lab3.home.news.NewsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            homeModule,
            localStorageModules,
            authModule
    )

val localStorageModules = module {
    single { createSharedPrefs(androidContext()) }
    single { SharedPrefImpl(get()) as SharedPref }
    single { createLocalStorage(androidContext()) }
    single { provideNewsDao(get()) }
    single { provideUserDao(get()) }
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}

internal fun createLocalStorage(context:Context) : AppLocalDatabase {
    return Room.databaseBuilder(context, AppLocalDatabase::class.java,"database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

internal fun provideUserDao(appDB: AppLocalDatabase): UserDao {
    return appDB.userDao()
}

internal fun provideNewsDao(appDB: AppLocalDatabase): NewsDao {
    return appDB.newsDao()
}

