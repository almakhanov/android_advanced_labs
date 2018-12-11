package kz.batana.finalandroid.add_contact

import io.reactivex.Flowable
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.util.IPresenter
import kz.batana.finalandroid.core.util.IView

interface AddContactContract {

    interface View: IView<Presenter> {
        fun msg(message: String)
        fun setGroups(list: ArrayList<ContactGroup>)
        fun goBack()
    }

    interface Presenter: IPresenter<View> {
        fun getGroups()
        fun saveContact(contact: Contact)

    }

    interface Repository{
        fun getGroups(): Flowable<List<ContactGroup>>
        fun saveContact(item: Contact): Flowable<Unit>
    }
}