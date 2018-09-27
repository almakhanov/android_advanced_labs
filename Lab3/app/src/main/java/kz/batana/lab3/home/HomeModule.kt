package kz.batana.lab3.home

import org.koin.dsl.module.module

val homeModule = module {
    factory { HomePresenter(get()) as HomeContract.Presenter }
    single { HomeRepository() as HomeContract.Repository }
}