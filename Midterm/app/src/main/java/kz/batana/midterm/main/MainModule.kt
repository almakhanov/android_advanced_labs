package kz.batana.midterm.main

import kz.batana.lab3.core.Constants
import kz.darlogistics.courier.core.createService
import org.koin.dsl.module.module

val mainModule = module {

    factory { MainPresenter(get()) as MainContract.Presenter }
    factory { MainRepository(get(), get(), get()) as MainContract.Repository }
    single { createService<MainService>(get(), Constants.URL) }
}