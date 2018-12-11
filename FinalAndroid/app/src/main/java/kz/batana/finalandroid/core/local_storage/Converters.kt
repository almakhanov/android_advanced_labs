package kz.batana.finalandroid.core.local_storage

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kz.batana.finalandroid.core.entity.ContactGroup

class Converters {
    private var gson = Gson()

    @TypeConverter
    fun stringToContactGroup(data: String?): ContactGroup{
        val listType = object : TypeToken<ContactGroup>() {}.type
        return gson.fromJson<ContactGroup>(data, listType)
    }

    @TypeConverter
    fun contactGroupToString(someObjects: ContactGroup): String {
        return gson.toJson(someObjects)
    }
}