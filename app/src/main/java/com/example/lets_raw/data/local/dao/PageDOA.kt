package com.example.lets_raw.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lets_raw.data.local.entity.PageEntity

@Dao
interface PageDao {

    @Insert
    suspend fun insertPage(page: PageEntity)

    @Update
    suspend fun updatePage(page: PageEntity)

    @Delete
    suspend fun deletePage(page: PageEntity)

    @Query("SELECT * FROM pages WHERE bookId = :bookId ORDER BY pageNumber ASC")
    fun getPagesForBook(bookId: Int): LiveData<List<PageEntity>>

    @Query("DELETE FROM pages WHERE bookId = :bookId")
    suspend fun deletePagesForBook(bookId: Int)
}