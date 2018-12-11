package kz.batana.finalandroid.core.local_storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup

@Database(entities = [Contact::class, ContactGroup::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppLocalDatabase: RoomDatabase() {

    abstract fun contactDao() : ContactDao

    abstract fun groupDao() : GroupDao

}