package com.example.lets_raw.fragments
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.adapters.HPBookItemAdapter
import com.example.lets_raw.adapters.HPContinueReadingAdapter
import com.example.lets_raw.models.HPBookItem

class FirstFragment:Fragment(R.layout.fragment_first_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_first_fragment, container, false)
        val searchFr1 = view.findViewById<SearchView>(R.id.searchview)

//    val searchButton = findViewById<SearchView>(R.id.searchview)
//
//    searchButton.setOnClickListener {
//        onSearchRequested()
//    }
        val hpBookRecyclerView = view.findViewById<RecyclerView>(R.id.home_page_trending_rv)
        val hpContinueReadingRecyclerView = view.findViewById<RecyclerView>(R.id.home_page_continue_reading_rv)
        hpBookRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hpContinueReadingRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val bookList = listOf(
            HPBookItem(0, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "random link", 223, 150),
            HPBookItem(1, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "random link", 251, 100),
            HPBookItem(2, "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", "random link", 317, 200),
            HPBookItem(3, "Harry Potter and the Goblet of Fire", "J.K. Rowling", "random link", 636, 150),
            HPBookItem(4, "Harry Potter and the Order of the Phoenix", "J.K. Rowling", "random link", 766, 150),
            HPBookItem(5, "Harry Potter and the Half-Blood Prince", "J.K. Rowling", "random link", 607, 150),
            HPBookItem(6, "Harry Potter and the Deathly Hallows", "J.K. Rowling", "random link", 607, 150)
        )

        hpBookRecyclerView.adapter = HPBookItemAdapter(bookList)
        hpContinueReadingRecyclerView.adapter = HPContinueReadingAdapter(bookList)
        return view
    }

}
