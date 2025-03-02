package com.example.lets_raw.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lets_raw.models.types.Status

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val coverLink: String,
    val pages: Int,
    val pagesRead: Int,
    val status: String

)