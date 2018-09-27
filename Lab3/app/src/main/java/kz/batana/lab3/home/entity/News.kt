package kz.batana.lab3.home.entity

import java.io.Serializable

data class News(
        val title : String,
        val date : String,
        val content : String,
        val imageUrl : String) : Serializable