package com.example.lets_raw.repository

import androidx.lifecycle.LiveData
import com.example.lets_raw.data.local.dao.BookDao
import com.example.lets_raw.data.local.entity.BookEntity
import com.example.lets_raw.models.BookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookDao: BookDao) {


    val allBooks: LiveData<List<BookEntity>> = bookDao.getAllBooks()


    fun getBookById(bookId: Int): LiveData<BookEntity> {
        return bookDao.getBookById(bookId)
    }

    fun getBooksByStatus(status: String): LiveData<List<BookEntity>> {
        return bookDao.getBooksByStatus(status)
    }


    suspend fun addBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.insertBook(book)
        }
    }


    suspend fun updateBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.updateBook(book)
        }
    }


    suspend fun deleteBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.deleteBook(book.id)
        }
    }
}