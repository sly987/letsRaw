package com.example.lets_raw.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.lets_raw.R
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.models.types.Status

class CPAddBookDialogFragment(private val onSave: (BookItem) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.cp_add_book_fragment, null)

        val titleEditText = view.findViewById<EditText>(R.id.cp_add_book_title)
        val authorEditText = view.findViewById<EditText>(R.id.cp_add_book_author)
        val coverEditText = view.findViewById<EditText>(R.id.cp_add_book_cover)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        view.findViewById<Button>(R.id.cp_add_book_save_button).setOnClickListener {
            val title = titleEditText.text.toString()
            val author = authorEditText.text.toString()
            val cover = coverEditText.text.toString()

            val newBook = BookItem(
                title = title,
                author = author,
                coverURL = cover,
                pages = 0,
                pagesRead = 0,
                status = Status.CREATED
            )
            onSave(newBook)
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.cp_add_book_cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
}