package com.example.lets_raw.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.models.HPBookItem
import com.google.android.material.imageview.ShapeableImageView

class HPContinueReadingAdapter(private val hpBookItemList: List<HPBookItem>) :
    RecyclerView.Adapter<HPContinueReadingAdapter.HPBookItemViewHolder>() {

    class HPBookItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.hp_continue_reading_title)
        val authorTextView: TextView = view.findViewById(R.id.hp_continue_reading_author)
        val coverImageView: ShapeableImageView = view.findViewById(R.id.hp_continue_reading_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HPBookItemViewHolder {
        val view = View.inflate(parent.context, R.layout.home_page_continue_reading_layout, null)
        return HPBookItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hpBookItemList.size
    }

    override fun onBindViewHolder(holder: HPBookItemViewHolder, position: Int) {
        val hpBookItem = hpBookItemList[position]
        holder.titleTextView.text = hpBookItem.title
        holder.authorTextView.text = hpBookItem.author

        val cardView = holder.itemView.findViewById<androidx.cardview.widget.CardView>(R.id.hp_continue_reading_progress_bar)
        val parentView = holder.itemView.findViewById<ViewGroup>(R.id.hp_continue_reading_progress_bar_parent)

        parentView.viewTreeObserver.addOnPreDrawListener {
            val totalWidth = parentView.width
            val layoutParams = cardView.layoutParams
            layoutParams.width = (hpBookItem.pagesRead.toFloat() / hpBookItem.pages * totalWidth).toInt()
            cardView.layoutParams = layoutParams
            true
        }
    }

}
