package com.example.lets_raw.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.models.BookItem
import com.google.android.material.imageview.ShapeableImageView

class HPBookItemAdapter(private var bookItemList: List<BookItem>) :
    RecyclerView.Adapter<HPBookItemAdapter.HPBookItemViewHolder>() {

        class HPBookItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val titleTextView: TextView = view.findViewById(R.id.hp_book_item_title)
            val coverImageView: ShapeableImageView = view.findViewById(R.id.hp_book_item_cover)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HPBookItemViewHolder {
        val view = View.inflate(parent.context, R.layout.home_page_book_item_layout, null)
        return HPBookItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookItemList.size
    }

    override fun onBindViewHolder(holder: HPBookItemViewHolder, position: Int) {
        val hpBookItem = bookItemList[position]
        holder.titleTextView.text = hpBookItem.title
    }

    fun updateData(newBooks: List<BookItem>) {
        bookItemList = newBooks
        notifyDataSetChanged()
    }


}
