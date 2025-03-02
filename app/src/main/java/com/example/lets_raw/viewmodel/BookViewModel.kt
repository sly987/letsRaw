package com.example.lets_raw.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lets_raw.data.local.AppDatabase
import com.example.lets_raw.data.local.entity.BookEntity
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.models.types.Status
import com.example.lets_raw.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {




    val allBooks: LiveData<List<BookItem>> = MediatorLiveData<List<BookItem>>().apply {
        addSource(bookRepository.allBooks) { books ->
            if (books != null) {
                value = books.map { mapEntityToBookItem(it) }
            }
        }
    }

    fun getBookById(bookId: Int): LiveData<BookItem> {
        val bookEntityLiveData = MediatorLiveData<BookItem>().apply {
            addSource(bookRepository.getBookById(bookId)) { bookEntity ->
                if (bookEntity != null) {
                    value = mapEntityToBookItem(bookEntity)
                }
            }
        }
        return bookEntityLiveData
    }

    fun getBooksByStatus(status: Status): LiveData<List<BookItem>> {
        val booksByStatus: LiveData<List<BookItem>> = MediatorLiveData<List<BookItem>>().apply {
            addSource(bookRepository.getBooksByStatus(status.name)) { book ->
                if (book != null) {
                    value = book.map { mapEntityToBookItem(it) }
                }
            }
        }
        return booksByStatus
    }

    fun addBook(book: BookItem) {
        viewModelScope.launch {
            bookRepository.addBook(mapBookItemToEntity(book))
        }
    }


    fun updateBook(book: BookItem) {
        viewModelScope.launch {
            bookRepository.updateBook(mapBookItemToEntity(book))
        }
    }


    fun deleteBook(book: BookItem) {
        viewModelScope.launch {
            bookRepository.deleteBook(mapBookItemToEntity(book))
        }
    }

    private fun mapBookItemToEntity(bookItem: BookItem): BookEntity {
        return BookEntity(
            id = 0,
            title = bookItem.title,
            author = bookItem.author,
            coverLink = bookItem.coverURL,
            pages = bookItem.pages,
            pagesRead = bookItem.pagesRead,
            status = bookItem.status.name
        )
    }

    private fun mapEntityToBookItem(bookEntity: BookEntity): BookItem {
        return BookItem(
            ID = bookEntity.id,
            title = bookEntity.title,
            author = bookEntity.author,
            coverURL = bookEntity.coverLink,
            pages = bookEntity.pages,
            pagesRead = bookEntity.pagesRead,
            status = Status.valueOf(bookEntity.status)
        )
    }
}