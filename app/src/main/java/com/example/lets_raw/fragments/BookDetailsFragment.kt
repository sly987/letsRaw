package com.example.lets_raw.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lets_raw.R
import com.example.lets_raw.interfaces.OnBookDataPassListener
import com.example.lets_raw.models.BookItem

class BookDetailsFragment : Fragment() {



    private var listener: OnBookDataPassListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Check if the parent fragment implements the interface
        val parentFragment = parentFragment
        if (parentFragment is OnBookDataPassListener) {
            listener = parentFragment
        }
        // Check if the hosting activity implements the interface
        else if (context is OnBookDataPassListener) {
            listener = context
        }
        // If neither implements the interface, throw an exception
        else {
            throw IllegalStateException("Parent fragment or activity must implement OnBookDataPassListener")
        }
    }


    private lateinit var book: BookItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_details_fragment, container, false)


        book = listener?.onBookDataPass() ?: throw IllegalStateException("Book not found")


        view.findViewById<TextView>(R.id.book_details_title).text = book.title
        view.findViewById<TextView>(R.id.book_details_author).text = book.author



        view.findViewById<Button>(R.id.book_details_exit_button).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Set up Read button
        view.findViewById<Button>(R.id.book_details_read_button).setOnClickListener {
            openBookReaderFragment()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun openBookReaderFragment() {

        val bookReaderFragment = BookReaderFragment()


        val args = Bundle()
        args.putInt("bookId", book.ID)
        bookReaderFragment.arguments = args

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.book_reader_fragment, bookReaderFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}