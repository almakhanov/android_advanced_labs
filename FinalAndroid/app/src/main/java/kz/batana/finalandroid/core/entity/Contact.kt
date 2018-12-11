package kz.batana.finalandroid.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Contact")
data class Contact(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var mobilePhone: String?,
        var homePhone: String?,
        var workPhone: String?,
        var phoneImageUrl: String?,
        var contactGroup: ContactGroup
): Serializable