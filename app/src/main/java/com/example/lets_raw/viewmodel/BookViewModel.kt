package com.example.lets_raw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lets_raw.data.local.AppDatabase
import com.example.lets_raw.data.local.entity.BookEntity
import com.example.lets_raw.models.BookItem
import com.example.lets_raw.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private val repository: BookRepository = bookRepository
    private val _bookItemLiveData = MediatorLiveData<BookItem>()
    val bookItemLiveData: LiveData<BookItem> get() = _bookItemLiveData



    val allBooks: LiveData<List<BookItem>> = MediatorLiveData<List<BookItem>>().apply {
        addSource(repository.allBooks) { books ->
            if (books != null) {
                value = books.map { mapEntityToBookItem(it) }
            }
        }
    }

    fun getBookById(bookId: Int) {
        val bookEntityLiveData = repository.getBookById(bookId)
        _bookItemLiveData.addSource(bookEntityLiveData) { bookEntity ->
            if (bookEntity != null) {
                _bookItemLiveData.value = mapEntityToBookItem(bookEntity)
            }
        }
    }

    // Add a new book
    fun addBook(book: BookItem) {
        viewModelScope.launch {
            repository.addBook(mapBookItemToEntity(book))
        }
    }

    // Update an existing book
    fun updateBook(book: BookItem) {
        viewModelScope.launch {
            repository.updateBook(mapBookItemToEntity(book))
        }
    }

    // Delete a book
    fun deleteBook(book: BookItem) {
        viewModelScope.launch {
            repository.deleteBook(mapBookItemToEntity(book))
        }
    }

    // Map BookItem to BookEntity
    private fun mapBookItemToEntity(bookItem: BookItem): BookEntity {
        return BookEntity(
            id = 0,
            title = bookItem.title,
            author = bookItem.author,
            coverLink = bookItem.coverURL,
            pages = bookItem.pages,
            pagesRead = bookItem.pagesRead
        )
    }

    private fun mapEntityToBookItem(bookEntity: BookEntity): BookItem {
        return BookItem(
            ID = bookEntity.id,
            title = bookEntity.title,
            author = bookEntity.author,
            coverURL = bookEntity.coverLink,
            pages = bookEntity.pages,
            pagesRead = bookEntity.pagesRead
        )
    }
}