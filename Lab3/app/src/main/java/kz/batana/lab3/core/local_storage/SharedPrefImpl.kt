package kz.batana.lab3.core.local_storage

import android.content.SharedPreferences
import kz.batana.lab3.core.Constants
import org.koin.standalone.KoinComponent

class SharedPrefImpl(private val pref : SharedPreferences) : SharedPref, KoinComponent {

    override fun saveUserEmail(email : String){
        pref.edit()
                .putString(Constants.EMAIL, email)
                .apply()
    }

    override fun setUserPassword(password : String){
        pref.edit()
                .putString(Constants.PASSWORD, password)
                .apply()
    }

    override fun clearUserEmail() {
        pref.edit()
                .remove(Constants.EMAIL)
                .apply()
    }

    override fun clearUserPassword() {
        pref.edit()
                .remove(Constants.PASSWORD)
                .apply()
    }

    override fun getUserEmail(): String? = pref.getString(Constants.EMAIL, Constants.EMPTY)

    override fun getUserPassword(): String? = pref.getString(Constants.PASSWORD, Constants.EMPTY)
}