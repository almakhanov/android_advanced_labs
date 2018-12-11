package kz.batana.finalandroid.main

import kz.batana.finalandroid.core.createService
import kz.batana.finalandroid.core.util.Constants
import org.koin.dsl.module.module

val mainModule = module {
    factory { MainPresenter(get()) as MainContract.Presenter }
    factory { MainRepository(get(), get(), get(), get()) as MainContract.Repository }
    single { createService<MainService>(get(), Constants.URL) }
}