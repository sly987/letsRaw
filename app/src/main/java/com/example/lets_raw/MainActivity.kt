package com.example.lets_raw

import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val firstFragment=FirstFragment()
        val secondFragment=SecondFragment()
        val thirdFragment=ThirdFragment()
        val fourthFragment=FourthFragment()
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setCurrentFragment(firstFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.button_home->setCurrentFragment(firstFragment)
                R.id.button_create->setCurrentFragment(secondFragment)
                R.id.button_library->setCurrentFragment(thirdFragment)
                R.id.button_account->setCurrentFragment(fourthFragment)

            }
            true
        }

    }


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,fragment)
            commit()
        }
}