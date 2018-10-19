package kz.batana.lab3.core.entity

import java.io.Serializable

data class Todo(
        var userId: Int,
        var id: Int,
        var title: String,
        var completed: Boolean
) : Serializable