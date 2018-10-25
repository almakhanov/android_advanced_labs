package kz.batana.lab3.core.entity

import java.io.Serializable

data class Post(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
) : Serializable