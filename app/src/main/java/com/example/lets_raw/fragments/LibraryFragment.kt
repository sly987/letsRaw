package com.example.lets_raw.fragments
import BookViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.adapters.LPBookAdapter
import com.example.lets_raw.data.local.AppDatabase
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.repository.BookRepository
import com.example.lets_raw.viewmodel.BookViewModel

class LibraryFragment:Fragment(R.layout.library_fragment) {
    private val books: List<BookItem> = emptyList()
    private lateinit var lpBookRecyclerView: RecyclerView
    private lateinit var lpBookAdapter: LPBookAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var bookRepository: BookRepository
    private lateinit var bookViewModelFactory: BookViewModelFactory
    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.library_fragment, container, false)
        setup()
        fetchDataForRV(view)

        return view

    }

    private fun setup() {
        appDatabase = AppDatabase.getDatabase(requireContext())
        bookRepository = BookRepository(appDatabase.bookDao())
        bookViewModelFactory = BookViewModelFactory(bookRepository)
        bookViewModel = ViewModelProvider(this, bookViewModelFactory)[BookViewModel::class.java]
    }

    private fun fetchDataForRV(view: View) {
        lpBookRecyclerView = view.findViewById(R.id.lp_book_rv)
        lpBookRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        lpBookAdapter = LPBookAdapter(emptyList())

        bookViewModel.allBooks.observe(viewLifecycleOwner) { books ->
            lpBookAdapter.updateData(books)
        }

        for (book in books) {
            bookViewModel.addBook(book)
        }

        lpBookRecyclerView.adapter = lpBookAdapter
    }

}
