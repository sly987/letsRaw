package com.example.lets_raw.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.R
import com.example.lets_raw.adapters.LPBookAdapter
import com.example.lets_raw.models.HPBookItem

class ThirdFragment:Fragment(R.layout.fragment_third_fragment) {
    private val books : List<HPBookItem> = listOf(
        HPBookItem(0, "The guy", "me", "random link", 200, 10),
        HPBookItem(1, "The other guy", "me", "random link", 300, 20),
        HPBookItem(2, "Some guy", "me", "random link", 150, 30),
        HPBookItem(3, "Hey, its the guy", "me", "random link", 400, 100),
        HPBookItem(4, "oh no its the guy", "me", "random link", 220, 20),
        HPBookItem(5, "is it the guy ?", "me", "random link", 250, 15),
        HPBookItem(6, "guy, its you ?", "me", "random link", 180, 35),
        HPBookItem(7, "don\'t tell me its guy", "me", "random link", 350, 85),
        HPBookItem(8, "everyone run its the guy ! ", "me", "random link", 100, 1),
        HPBookItem(9, "Hi guy", "me", "random link", 200, 100),
        HPBookItem(10, "Goodbye guy", "me", "random link", 220, 95),
        HPBookItem(11, "Come here guy", "me", "random link", 270, 64),
        HPBookItem(12, "Go away guy", "me", "random link", 160, 34),
        HPBookItem(13, "Goddamn it guy", "me", "random link", 300, 74),
        HPBookItem(14, "Fuck you guy", "me", "random link", 180, 51),
        HPBookItem(15, "Guy, are you dumb ?", "me", "random link", 220, 65),
        HPBookItem(16, "omg... guy", "me", "random link", 350, 85),
        HPBookItem(17, "guy", "me", "random link", 200, 100),
        HPBookItem(18, "guyguy", "me", "random link", 230, 45),
        HPBookItem(19, "guyguyguy", "me", "random link", 180, 78),
        HPBookItem(20, "guyguyguyguy", "me", "random link", 400, 0),
        HPBookItem(21, "dumb ass guy", "me", "random link", 120, 12),
        HPBookItem(22, "stupid guy", "me", "random link", 250, 40),
        HPBookItem(23, "pretty guy ;)", "me", "random link", 300, 78)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_third_fragment, container, false)
        val lpBookRecyclerView = view.findViewById<RecyclerView>(R.id.lp_book_rv)
        lpBookRecyclerView.layoutManager = GridLayoutManager(this.context,2)
        lpBookRecyclerView.adapter = LPBookAdapter(books)
        return view

    }

}
