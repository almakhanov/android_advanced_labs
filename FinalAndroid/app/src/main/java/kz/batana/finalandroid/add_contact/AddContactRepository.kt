package kz.batana.finalandroid.add_contact

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import kz.batana.finalandroid.core.local_storage.ContactDao
import kz.batana.finalandroid.core.local_storage.GroupDao
import kz.batana.finalandroid.core.local_storage.SharedPref
import kz.batana.finalandroid.main.MainService

class AddContactRepository(private val service: MainService,
                           private val sharedPref: SharedPref,
                           private val groupDao: GroupDao,
                           private val contactDao: ContactDao) : AddContactContract.Repository{

    override fun saveContact(item: Contact): Flowable<Unit> {
        return Flowable.just(contactDao.insert(item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getGroups(): Flowable<List<ContactGroup>> {
        return groupDao.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}