package com.example.lets_raw
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainer
import java.util.zip.Inflater

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
        return view
    }

}
