package com.example.lets_raw.fragments
import BookViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.adapters.CPCreatedBookAdapter
import com.example.lets_raw.data.local.AppDatabase
import com.example.lets_raw.models.types.Status
import com.example.lets_raw.repository.BookRepository
import com.example.lets_raw.viewmodel.BookViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateFragment:Fragment(R.layout.create_fragment) {
    private lateinit var appDatabase: AppDatabase
    private lateinit var bookRepository: BookRepository
    private lateinit var bookViewModelFactory: BookViewModelFactory
    private lateinit var bookViewModel: BookViewModel
    private lateinit var cpCreatedBookAdapter: CPCreatedBookAdapter
    private lateinit var cpCreatedBookRecyclerView: RecyclerView
    private lateinit var cpAddBookButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_fragment, container, false)
        setup()
        fetchDataRV(view)
        watchForChanges()
        handleAddBook(view)

        return view
    }

    private fun handleAddBook(view: View) {
        cpAddBookButton = view.findViewById(R.id.cp_add_book_fab)
        cpAddBookButton.setOnClickListener {
            val dialog = CPAddBookDialogFragment { newBook ->
                bookViewModel.addBook(newBook)
            }
            dialog.show(parentFragmentManager, "CPAddBookDialogFragment")
        }
    }

    private fun watchForChanges() {
        bookViewModel.getBooksByStatus(Status.CREATED)
            .observe(viewLifecycleOwner) { books ->
                cpCreatedBookAdapter.updateData(books)
            }
    }

    private fun fetchDataRV(view: View) {
        cpCreatedBookRecyclerView = view.findViewById(R.id.cp_created_books_rv)
        cpCreatedBookRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        cpCreatedBookAdapter = CPCreatedBookAdapter(emptyList())
        cpCreatedBookRecyclerView.adapter = cpCreatedBookAdapter
    }

    private fun setup() {
        appDatabase = AppDatabase.getDatabase(requireContext())
        bookRepository = BookRepository(appDatabase.bookDao())
        bookViewModelFactory = BookViewModelFactory(bookRepository)
        bookViewModel = ViewModelProvider(this, bookViewModelFactory)[BookViewModel::class.java]
    }
}
