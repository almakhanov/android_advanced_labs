package kz.batana.finalandroid

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import kz.batana.finalandroid.add_contact.addModule
import kz.batana.finalandroid.core.coreModule
import kz.batana.finalandroid.core.local_storage.*
import kz.batana.finalandroid.core.util.Constants
import kz.batana.finalandroid.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            localStorageModules,
            coreModule,
            mainModule,
            addModule
    )

val localStorageModules = module {
    single { createSharedPrefs(androidContext()) }
    single { SharedPrefImpl(get()) as SharedPref }
    single { createLocalStorage(androidContext()) }
    single { provideGroupDao(get()) }
    single { provideContactDao(get()) }
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}

internal fun createLocalStorage(context:Context) : AppLocalDatabase {
    return Room.databaseBuilder(context, AppLocalDatabase::class.java,"final_db3")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

internal fun provideContactDao(appDB: AppLocalDatabase): ContactDao {
    return appDB.contactDao()
}

internal fun provideGroupDao(appDB: AppLocalDatabase): GroupDao {
    return appDB.groupDao()
}

