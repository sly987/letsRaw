package com.example.lets_raw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lets_raw.data.local.entity.PageEntity
import com.example.lets_raw.models.PageItem
import com.example.lets_raw.repository.PageRepository
import kotlinx.coroutines.launch

class PageViewModel(private val repository: PageRepository) : ViewModel() {



    fun getPagesForBook(bookId: Int): LiveData<List<PageItem>> {
        val pagesForBook: LiveData<List<PageItem>> = MediatorLiveData<List<PageItem>>().apply {
            addSource(repository.getPagesForBook(bookId)) { page ->
                if (page != null) {
                    value = page.map { mapPageEntityToPageItem(it) }
                }
            }
        }
        return pagesForBook
    }

    suspend fun insertPage(page: PageItem) {
        viewModelScope.launch {
            val pageEntity = mapPageItemToPageEntity(page)
            repository.insertPage(pageEntity)
        }
    }

    suspend fun updatePage(page: PageItem) {
        viewModelScope.launch {
            val pageEntity = mapPageItemToPageEntity(page)
            repository.updatePage(pageEntity)
        }
    }

    suspend fun deletePage(page: PageItem) {
        viewModelScope.launch{
            val pageEntity = mapPageItemToPageEntity(page)
            repository.deletePage(pageEntity)
        }
    }

    suspend fun deletePagesForBook(bookId: Int) {
        repository.deletePagesForBook(bookId)
    }

    private fun mapPageEntityToPageItem(pageEntity: PageEntity): PageItem {
        return PageItem(
            pageId = pageEntity.id,
            bookId = pageEntity.bookId,
            pageNumber = pageEntity.pageNumber,
            content = pageEntity.content
        )
    }

    private fun mapPageItemToPageEntity(pageItem: PageItem): PageEntity {
        return PageEntity(
            id = pageItem.pageId,
            bookId = pageItem.bookId,
            pageNumber = pageItem.pageNumber,
            content = pageItem.content
        )
    }
}