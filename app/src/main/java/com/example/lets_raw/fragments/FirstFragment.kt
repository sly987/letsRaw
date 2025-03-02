package com.example.lets_raw.fragments
import BookViewModelFactory
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.adapters.HPBookItemAdapter
import com.example.lets_raw.adapters.HPContinueReadingAdapter
import com.example.lets_raw.data.local.AppDatabase
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.repository.BookRepository
import com.example.lets_raw.viewmodel.BookViewModel

class FirstFragment:Fragment(R.layout.fragment_first_fragment) {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var hpBookRecyclerView: RecyclerView
    private lateinit var hpContinueReadingRecyclerView: RecyclerView
    private lateinit var bookViewModelFactory: BookViewModelFactory
    private lateinit var appDatabase: AppDatabase
    private lateinit var bookRepository: BookRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_first_fragment, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        val hpBookAdapter = HPBookItemAdapter(emptyList())
        val hpContinueReadingAdapter = HPContinueReadingAdapter(emptyList())
        bookRepository = BookRepository(appDatabase.bookDao())
        bookViewModelFactory = BookViewModelFactory(bookRepository)
        bookViewModel = ViewModelProvider(this, bookViewModelFactory)[BookViewModel::class.java]

        hpBookRecyclerView = view.findViewById(R.id.home_page_trending_rv)
        hpContinueReadingRecyclerView = view.findViewById(R.id.home_page_continue_reading_rv)
        hpBookRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hpContinueReadingRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hpContinueReadingRecyclerView.adapter = hpContinueReadingAdapter
        hpBookRecyclerView.adapter = hpBookAdapter


        val bookList = listOf(
            BookItem(title = "Harry Potter and the Philosopher's Stone", author = "J.K. Rowling", coverURL = "random link", pages = 223, pagesRead =  150),
            BookItem(title ="Harry Potter and the Chamber of Secrets", author = "J.K. Rowling", coverURL = "random link", pages = 251, pagesRead =  100),
            BookItem(title ="Harry Potter and the Prisoner of Azkaban", author = "J.K. Rowling", coverURL = "random link", pages = 317, pagesRead =  200),
            BookItem(title ="Harry Potter and the Goblet of Fire", author = "J.K. Rowling", coverURL = "random link", pages = 636, pagesRead =  150),
            BookItem(title ="Harry Potter and the Order of the Phoenix", author = "J.K. Rowling", coverURL = "random link", pages = 766, pagesRead =  150),
            BookItem(title ="Harry Potter and the Half-Blood Prince", author = "J.K. Rowling", coverURL = "random link", pages = 607, pagesRead =  150),
            BookItem(title ="Harry Potter and the Deathly Hallows", author = "J.K. Rowling", coverURL = "random link", pages = 607, pagesRead =  150)
        )
        bookList.forEach { book ->
            bookViewModel.addBook(book)
        }

        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                hpBookAdapter.updateData(it)
                hpContinueReadingAdapter.updateData(it)
            }
        })

        return view
    }

}
