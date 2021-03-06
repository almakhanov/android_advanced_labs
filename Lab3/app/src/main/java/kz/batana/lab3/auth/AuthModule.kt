package kz.batana.lab3.auth

import org.koin.dsl.module.module

val authModule = module {
    factory { AuthPresenter(get()) as AuthContract.Presenter }
    factory { AuthRepository(get(), get()) as AuthContract.Repository}
}