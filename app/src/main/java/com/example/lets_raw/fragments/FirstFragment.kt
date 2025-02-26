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
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_page_trending_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val bookList = listOf(
            HPBookItem(0,"Title 1", "randomLink"),
            HPBookItem(1,"Title 2", "randomLink"),
            HPBookItem(2,"Title 3", "randomLink"),
            HPBookItem(3,"Title 4", "randomLink"),
            HPBookItem(4,"Title 5", "randomLink"),
            HPBookItem(5,"Title 6", "randomLink"),
            HPBookItem(6,"Title 7", "randomLink"),
            HPBookItem(7,"Title 8", "randomLink"),
            HPBookItem(8,"Title 9", "randomLink"),
            HPBookItem(9,"Title 10", "randomLink"),
            HPBookItem(10,"Title 11", "randomLink"),
            HPBookItem(11,"Title 12", "randomLink"),
            HPBookItem(12,"Title 13", "randomLink"),
            HPBookItem(13,"Title 14", "randomLink"),
            HPBookItem(14,"Title 15", "randomLink"),
            HPBookItem(15,"Title 16", "randomLink"),
        )

        recyclerView.adapter = HPBookItemAdapter(bookList)

        return view
    }

}
