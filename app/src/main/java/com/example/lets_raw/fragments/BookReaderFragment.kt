package com.example.lets_raw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lets_raw.R

class BookReaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_reader_layout, container, false)

        val bookId = arguments?.getInt("bookId") ?: throw IllegalStateException("Book ID not found")

        view.findViewById<TextView>(R.id.book_reader_content).text = "Loading book content for ID: $bookId..."

        view.findViewById<Button>(R.id.book_reader_back_button).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}