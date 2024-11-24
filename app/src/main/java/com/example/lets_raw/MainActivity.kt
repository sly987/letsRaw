package com.example.lets_raw

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lets_raw.views.models.BookModel

private val books : List<BookModel> = listOf(
        BookModel("The guy","me",10,10),
        BookModel("The other guy","me",10,20),
        BookModel("Some guy","me",10,30),
        BookModel("Hey, its the guy","me",10,100),
        BookModel("oh no its the guy","me",10,20),
        BookModel("is it the guy ?","me",10,15),
        BookModel("guy, its you ?","me",10,35),
        BookModel("dont tell me its guy","me",10,85),
        BookModel("everyone run its the guy ! ","me",10,1),
        BookModel("Hi guy","me",10,100),
        BookModel("Goodbye guy","me",10,95),
        BookModel("Come here guy","me",10,64),
        BookModel("Go away guy","me",10,34),
        BookModel("Goddamn it guy","me",10,74),
        BookModel("Fuck you guy","me",10,51),
        BookModel("Guy, are you dumb ?","me",10,65),
        BookModel("omg... guy","me",10,85),
        BookModel("guy","me",10,100),
        BookModel("guyguy","me",10,45),
        BookModel("guyguyguy","me",10,78),
        BookModel("guyguyguyguy","me",10,0),
        BookModel("dumb ass guy","me",10,12),
        BookModel("stupid guy","me",10,40),
        BookModel("pretty guy ;)","me",10,78),

)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    val bookList = findViewById<RecyclerView>(R.id.book_list)
        bookList.layoutManager = GridLayoutManager(this,2)
    bookList.adapter = BookAdapter(books)

    }
}