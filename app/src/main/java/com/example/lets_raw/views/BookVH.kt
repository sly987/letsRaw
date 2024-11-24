package com.example.lets_raw.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R

class BookVH (bookView : View) : RecyclerView.ViewHolder(bookView) {
    val bookTitle = bookView.findViewById<TextView>(R.id.book_title)
    val bookAuthor = bookView.findViewById<TextView>(R.id.book_author)
    val bookCover = bookView.findViewById<ImageView>(R.id.book_cover_image)
    val bookProgress = bookView.findViewById<CardView>(R.id.book_progress_bar)
}