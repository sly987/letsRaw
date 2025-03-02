package com.example.lets_raw.fragments
import BookViewModelFactory
import androidx.fragment.app.Fragment
import android.os.Bundle
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
import com.example.lets_raw.interfaces.OnBookDataPassListener
import com.example.lets_raw.interfaces.OnBookSelectedListener
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.models.types.Status
import com.example.lets_raw.repository.BookRepository
import com.example.lets_raw.viewmodel.BookViewModel

class HomePageFragment:Fragment(R.layout.home_page_fragment), OnBookSelectedListener, OnBookDataPassListener {
    private val books: List<BookItem> = listOf(
            BookItem(title = "Harry Potter and the Philosopher's Stone", author = "J.K. Rowling", coverURL = "random link", pages = 223, pagesRead =  150, status = Status.CREATED),
            BookItem(title ="Harry Potter and the Chamber of Secrets", author = "J.K. Rowling", coverURL = "random link", pages = 251, pagesRead =  100, status = Status.CREATED),
            BookItem(title ="Harry Potter and the Prisoner of Azkaban", author = "J.K. Rowling", coverURL = "random link", pages = 317, pagesRead =  200, status = Status.IMPORTED),
            BookItem(title ="Harry Potter and the Goblet of Fire", author = "J.K. Rowling", coverURL = "random link", pages = 636, pagesRead =  150, status = Status.CREATED),
            BookItem(title ="Harry Potter and the Order of the Phoenix", author = "J.K. Rowling", coverURL = "random link", pages = 766, pagesRead =  150, status = Status.IMPORTED),
            BookItem(title ="Harry Potter and the Half-Blood Prince", author = "J.K. Rowling", coverURL = "random link", pages = 607, pagesRead =  150, status = Status.CREATED),
            BookItem(title ="Harry Potter and the Deathly Hallows", author = "J.K. Rowling", coverURL = "random link", pages = 607, pagesRead =  150, status = Status.IMPORTED)
        )
    private lateinit var bookViewModel: BookViewModel
    private lateinit var hpBookRecyclerView: RecyclerView
    private lateinit var hpContinueReadingRecyclerView: RecyclerView
    private lateinit var bookViewModelFactory: BookViewModelFactory
    private lateinit var appDatabase: AppDatabase
    private lateinit var bookRepository: BookRepository
    private lateinit var hpBookAdapter: HPBookItemAdapter
    private lateinit var hpContinueReadingAdapter: HPContinueReadingAdapter
    private lateinit var selectedBook: BookItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)

        setup()
        fetchDataForRvs(view)
        watchForChanges()

        //Run this block when it's the first time installing the app
//        for (book in books) {
//            bookViewModel.addBook(book)
//        }

        return view
    }

    private fun fetchDataForRvs(view: View) {
        hpBookRecyclerView = view.findViewById(R.id.home_page_trending_rv)
        hpContinueReadingRecyclerView = view.findViewById(R.id.home_page_continue_reading_rv)
        hpBookAdapter = HPBookItemAdapter(books, this)
        hpContinueReadingAdapter = HPContinueReadingAdapter(books)
        hpBookRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hpContinueReadingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hpContinueReadingRecyclerView.adapter = hpContinueReadingAdapter
        hpBookRecyclerView.adapter = hpBookAdapter
    }

    private fun watchForChanges() {
        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                hpBookAdapter.updateData(it)
                hpContinueReadingAdapter.updateData(it)
            }
        })
    }

    private fun setup() {
        appDatabase = AppDatabase.getDatabase(requireContext())
        bookRepository = BookRepository(appDatabase.bookDao())
        bookViewModelFactory = BookViewModelFactory(bookRepository)
        bookViewModel = ViewModelProvider(this, bookViewModelFactory)[BookViewModel::class.java]
    }

    private fun openBookDetailsFragment(book: BookItem) {
        selectedBook = book
        val bookDetailsFragment = BookDetailsFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.book_details_fragment, bookDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBookSelected(book: BookItem) {
        openBookDetailsFragment(book)
    }

    override fun onBookDataPass(): BookItem {
        return selectedBook
    }

}
