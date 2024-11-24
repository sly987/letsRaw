package com.example.lets_raw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.views.BookVH
import com.example.lets_raw.views.models.BookModel

class BookAdapter (private val bookList : List<BookModel>) : RecyclerView.Adapter<BookVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_layout,parent,false)
        return BookVH(view)
    }

    override fun getItemCount(): Int {
        return this.bookList.size
    }

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val book = this.bookList[position]
        val cardView = holder.bookProgress
        val parentView = cardView.parent as ViewGroup

        parentView.post {
            val layoutParams = cardView.layoutParams
            layoutParams.width = parentView.width * book.progress / 100
            cardView.layoutParams = layoutParams
        }

        holder.bookTitle.text = book.title
        holder.bookAuthor.text = book.author
        holder.bookCover.setImageResource(R.drawable.camera_svgrepo_com)
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(16, 16, 16, 16) // Apply margins (in pixels)
        holder.itemView.layoutParams = layoutParams
    }
}