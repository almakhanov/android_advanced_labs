package kz.batana.lab3.core.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
        var name: String,
        @PrimaryKey
        var email: String,
        var password: String
): Serializable