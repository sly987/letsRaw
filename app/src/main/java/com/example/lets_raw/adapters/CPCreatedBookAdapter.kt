package com.example.lets_raw.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.models.BookItem

class CPCreatedBookAdapter(private var bookItems: List<BookItem>): RecyclerView.Adapter<CPCreatedBookAdapter.CPCreatedBookViewHolder>() {

    class CPCreatedBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.cp_created_book_title)
        val authorTextView: TextView = view.findViewById(R.id.cp_created_books_author)
        val pagesTextView: TextView = view.findViewById(R.id.cp_created_book_nb_pages_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CPCreatedBookViewHolder {
        val view = View.inflate(parent.context, R.layout.cp_created_book_vh_layout, null)
        return CPCreatedBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookItems.size
    }

    override fun onBindViewHolder(holder: CPCreatedBookViewHolder, position: Int) {
        val cpBookItem = bookItems[position]
        holder.titleTextView.text = cpBookItem.title
        holder.authorTextView.text = cpBookItem.author
        holder.pagesTextView.text = String.format(cpBookItem.pages.toString())
    }

    fun updateData(newBooks: List<BookItem>) {
        bookItems = newBooks
        notifyDataSetChanged()
    }
}