package kz.batana.finalandroid.main

import android.annotation.SuppressLint
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.util.BasePresenter
import kz.batana.finalandroid.core.util.Logger

class MainPresenter(private val repository: MainContract.Repository)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun insertGroup(list: ArrayList<ContactGroup>) {
        for (it in list){
            repository.insertGroup(it)
                    .subscribe()
        }
    }

    @SuppressLint("CheckResult")
    override fun getGroups() {
        repository.getGroups().subscribe(
                        {
                            Logger.msg("accepted pres", it.size)
                            getView()?.setGroups(it as ArrayList<ContactGroup>)
                        },
                        {
                            Logger.msg("accepted", it.message)
                            getView()?.msg(it.message!!)
                        })
    }

    @SuppressLint("CheckResult")
    override fun getContact() {
        repository.getContacts().subscribe(
                {
                    Logger.msg("accepted pres", it.size)
                    getView()?.setContacts(it as ArrayList<Contact>)
                })
    }
//    @SuppressLint("CheckResult")
//    override fun getTodos(){
//        repository.getTodos()
//                .subscribe(
//                        {
//                            getView()?.sendTodos(it as ArrayList<ContactGroup>)
//                        },
//                        {
//                            getView()?.msg(it.message!!)
//                        })
//    }
//
//    @SuppressLint("CheckResult")
//    override fun getDones() {
//        repository.getTodosDone()
//                .subscribe(
//                        {
//                            getView()?.sendTodosDone(it as ArrayList<ContactGroup>)
//                        },
//                        {
//                            getView()?.msg(it.message!!)
//                        })    }
//
//    @SuppressLint("CheckResult")
//    fun getTodosDone() {
//        repository.getTodosDone()
//                .subscribe(
//                        {
//                            getView()?.sendTodosDone(it as ArrayList<ContactGroup>)
//                        },
//                        {
//                            getView()?.msg(it.message!!)
//                        })
//    }
//
//    @SuppressLint("CheckResult")
//    override fun addTodo(todo: ContactGroup) {
//        repository.addTodo(todo).subscribe(
//                {
//                    getTodos()
//                },
//                {
//                    getView()?.msg(it.message!!)
//                })
//    }

    override fun viewIsReady() {}

    override fun destroy() {}
}