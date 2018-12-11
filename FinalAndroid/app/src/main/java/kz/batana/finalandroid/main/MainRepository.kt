package kz.batana.finalandroid.main

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.local_storage.ContactDao
import kz.batana.finalandroid.core.local_storage.GroupDao
import kz.batana.finalandroid.core.local_storage.SharedPref

class MainRepository(private val service: MainService,
                     private val sharedPref: SharedPref,
                     private val groupDao: GroupDao,
                     private val contactDao: ContactDao): MainContract.Repository {


    override fun insertGroup(item: ContactGroup): Flowable<Unit> {
        return Flowable.just(groupDao.insert(item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getContacts(): Flowable<List<Contact>> {
        return contactDao.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getGroups(): Flowable<List<ContactGroup>> {
        return groupDao.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


//    override fun doneTodo(todo: ContactGroup) : Flowable<Unit> {
//        return Flowable.just(contactDao.doneTodo(todo.id))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getTodos(): Flowable<List<ContactGroup>> {
//        return contactDao.getTodos(sharedPref.getUserEmail()!!)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun getTodosDone(): Flowable<List<ContactGroup>>{
//        return contactDao.getTodosDone(sharedPref.getUserEmail()!!)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun addTodo(todo: ContactGroup): Flowable<Unit> {
//        return Flowable.just(contactDao.insertTodo(todo))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }


}