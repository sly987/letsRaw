package com.example.lets_raw.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.models.HPBookItem

class LPBookAdapter (private val hpBookItemList : List<HPBookItem>) : RecyclerView.Adapter<LPBookAdapter.HPBookItemViewHolder>() {

    class HPBookItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.lp_book_title)
        val authorTextView: TextView = view.findViewById(R.id.lp_book_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HPBookItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lp_book_layout,parent,false)
        return HPBookItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.hpBookItemList.size
    }

    override fun onBindViewHolder(holder: HPBookItemViewHolder, position: Int) {
        val hpBookItem = hpBookItemList[position]
        holder.titleTextView.text = hpBookItem.title
        holder.authorTextView.text = hpBookItem.author

        val cardView = holder.itemView.findViewById<CardView>(R.id.lp_book_progress_bar)
        val parentView = holder.itemView.findViewById<ViewGroup>(R.id.lp_book_progress_bar_parent)

        parentView.viewTreeObserver.addOnPreDrawListener {
            val totalWidth = parentView.width
            val layoutParams = cardView.layoutParams
            layoutParams.width = (hpBookItem.progress.toFloat() / hpBookItem.pages * totalWidth).toInt()
            cardView.layoutParams = layoutParams
            true
        }
    }
}