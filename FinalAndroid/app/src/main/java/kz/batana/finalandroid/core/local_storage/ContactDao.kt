package kz.batana.finalandroid.core.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.finalandroid.core.entity.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Contact)

    @Query("SELECT * FROM Contact WHERE id=:id ")
    fun getById(id: Int): Flowable<Contact>

    @Query("SELECT * FROM Contact")
    fun get(): Flowable<List<Contact>>

    @Query("DELETE FROM Contact")
    fun delete()
}