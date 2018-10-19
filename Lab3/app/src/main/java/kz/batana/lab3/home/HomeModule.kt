package kz.batana.lab3.home

import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.createService
import kz.batana.lab3.home.todo.TodoContract
import kz.batana.lab3.home.todo.TodoPresenter
import kz.batana.lab3.home.todo.TodoService
import org.koin.dsl.module.module

val homeModule = module {
    factory { HomePresenter(get()) as HomeContract.Presenter }
    factory { HomeRepository(get(), get()) as HomeContract.Repository }
    factory { TodoPresenter(get()) as TodoContract.Presenter }
    single { createService<TodoService>(get(), Constants.URL)}
}