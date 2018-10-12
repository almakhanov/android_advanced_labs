package kz.batana.lab3.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var title : String,
        var desc : String,
        var done: Int,
        var user_email: String
) : Serializable