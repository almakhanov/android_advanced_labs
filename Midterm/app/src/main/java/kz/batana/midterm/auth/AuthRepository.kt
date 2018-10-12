package kz.batana.midterm.auth

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.khanproject.Logger
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.entity.User
import kz.batana.lab3.core.local_storage.SharedPref

class AuthRepository(private val userDao: UserDao, private val pref: SharedPref): AuthContract.Repository {

    //KeyStore
    //ipconfig

    override fun getUserByEmail(email: String) : Flowable<User> {
        Logger.msg("accepted", "getUserByEmail")
        return userDao.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun saveUserLocally(user: User) {
        pref.saveUserEmail(user.email)
        pref.setUserPassword(user.password)
    }
}