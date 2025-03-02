package com.example.lets_raw.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lets_raw.data.local.entity.BookEntity

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("DELETE FROM books WHERE id = :bookId")
    suspend fun deleteBook(bookId: Int)

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookById(bookId: Int): LiveData<BookEntity>

    @Query("SELECT * FROM books WHERE status = :status")
    fun getBooksByStatus(status: String): LiveData<List<BookEntity>>

}