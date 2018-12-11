package kz.batana.finalandroid.add_contact

import android.annotation.SuppressLint
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.util.BasePresenter
import kz.batana.finalandroid.core.util.Logger


class AddContactPresenter(private val repository: AddContactContract.Repository)
    : BasePresenter<AddContactContract.View>(), AddContactContract.Presenter{
    override fun viewIsReady() {

    }

    override fun destroy() {
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

    override fun saveContact(contact: Contact) {
        repository.saveContact(contact)
                .doFinally{
                    getView()?.goBack()
                }
                .subscribe()
    }
}