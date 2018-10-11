package kz.batana.lab3.auth

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.lab3.core.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE email=:email ")
    fun getUserByEmail(email: String): Flowable<User>

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flowable<List<User>>

    @Query("DELETE FROM user")
    fun delete()
}