package com.example.lets_raw.models

import com.example.lets_raw.models.types.Status

data class BookItem(val ID: Int = 0, val title: String, val author: String, val coverURL: String, val pages: Int, val pagesRead: Int, val status: Status)
