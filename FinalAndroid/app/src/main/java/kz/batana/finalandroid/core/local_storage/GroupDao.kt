package kz.batana.finalandroid.core.local_storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import kz.batana.finalandroid.core.entity.ContactGroup

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ContactGroup)

    @Query("SELECT * FROM ContactGroup WHERE id=:id ")
    fun getById(id: Int): Flowable<ContactGroup>

    @Query("SELECT * FROM ContactGroup")
    fun get(): Flowable<List<ContactGroup>>

    @Query("DELETE FROM ContactGroup")
    fun delete()
}