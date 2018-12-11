package kz.batana.finalandroid.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ContactGroup")
data class ContactGroup(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String?,
        var priority: Int?
) : Serializable