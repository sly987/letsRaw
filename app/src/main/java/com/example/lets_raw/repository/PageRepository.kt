package com.example.lets_raw.repository

import androidx.lifecycle.LiveData
import com.example.lets_raw.data.local.dao.PageDao
import com.example.lets_raw.data.local.entity.PageEntity

class PageRepository(private val pageDao: PageDao) {

    fun getPagesForBook(bookId: Int): LiveData<List<PageEntity>> {
        return pageDao.getPagesForBook(bookId)
    }

    suspend fun insertPage(page: PageEntity) {
        pageDao.insertPage(page)
    }

    suspend fun updatePage(page: PageEntity) {
        pageDao.updatePage(page)
    }

    suspend fun deletePage(page: PageEntity) {
        pageDao.deletePage(page)
    }

    suspend fun deletePagesForBook(bookId: Int) {
        pageDao.deletePagesForBook(bookId)
    }
}