package kz.batana.midterm.auth

import io.reactivex.Flowable
import kz.batana.lab3.core.entity.User
import kz.darlogistics.courier.core.util.IPresenter
import kz.darlogistics.courier.core.util.IView

interface AuthContract {
    interface View: IView<Presenter>{
        fun onSuccessLogin(user: User)
        fun onFailedLogin(msg: String)
    }

    interface Presenter: IPresenter<View>{
        fun login(email: String, password: String)
        fun saveUserLocally(user: User)
    }

    interface Repository{
        fun getUserByEmail(email: String) : Flowable<User>
        fun saveUserLocally(user: User)
    }
}