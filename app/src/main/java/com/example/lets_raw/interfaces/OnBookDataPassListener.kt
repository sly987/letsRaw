package com.example.lets_raw.interfaces

import com.example.lets_raw.models.BookItem

interface OnBookDataPassListener {
    fun onBookDataPass(): BookItem
}