package kz.batana.lab3.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news")
data class News(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var title : String,
        var date : String,
        var content : String,
        var imageUrl : String
) : Serializable