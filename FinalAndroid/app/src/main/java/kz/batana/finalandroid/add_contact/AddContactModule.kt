package kz.batana.finalandroid.add_contact

import org.koin.dsl.module.module

val addModule = module {
    factory { AddContactPresenter(get()) as AddContactContract.Presenter }
    factory { AddContactRepository(get(), get(), get(), get()) as AddContactContract.Repository }
}