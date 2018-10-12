package kz.batana.midterm.auth

import android.annotation.SuppressLint
import kz.batana.lab3.core.entity.User
import kz.darlogistics.courier.core.util.BasePresenter

class AuthPresenter(private val repository: AuthContract.Repository)
    : AuthContract.Presenter, BasePresenter<AuthContract.View>(){

    override fun viewIsReady() {}

    override fun destroy() {}

    @SuppressLint("CheckResult")
    override fun login(email: String, password: String) {
        repository.getUserByEmail(email)
                .subscribe(
                        {
                            if(it.password == password){
                                getView()?.onSuccessLogin(it)
                            }else{
                                getView()?.onFailedLogin("Incorrect email or password!")
                            }
                        },
                        {
                            getView()?.onFailedLogin("Incorrect email!")
                        }
                )
    }

    override fun saveUserLocally(user: User) {
        repository.saveUserLocally(user)
    }

}