package kz.batana.finalandroid.core.local_storage

interface SharedPref {
    fun saveUserEmail(email : String)

    fun getUserEmail() : String?

    fun setUserPassword(password : String)

    fun getUserPassword() : String?

    fun clearUserEmail()

    fun clearUserPassword()
}