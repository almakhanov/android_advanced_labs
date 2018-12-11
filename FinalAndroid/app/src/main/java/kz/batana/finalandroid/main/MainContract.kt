package kz.batana.finalandroid.main

import io.reactivex.Flowable
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.util.IPresenter
import kz.batana.finalandroid.core.util.IView

interface MainContract {

    interface View: IView<Presenter> {
        fun msg(message: String)
        fun setGroups(list: ArrayList<ContactGroup>)
        fun setContacts(arrayList: ArrayList<Contact>)
    }

    interface Presenter: IPresenter<View> {
        fun insertGroup(list: ArrayList<ContactGroup>)
        fun getGroups()
        fun getContact()

    }

    interface Repository{
        fun insertGroup(item: ContactGroup): Flowable<Unit>
        fun getGroups(): Flowable<List<ContactGroup>>
        fun getContacts(): Flowable<List<Contact>>

    }
}