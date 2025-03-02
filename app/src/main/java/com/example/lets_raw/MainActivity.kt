package com.example.lets_raw

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lets_raw.fragments.HomePageFragment
import com.example.lets_raw.fragments.AccountCenterFragment
import com.example.lets_raw.fragments.CreateFragment
import com.example.lets_raw.fragments.LibraryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val homePageFragment= HomePageFragment()
        val createFragment= CreateFragment()
        val libraryFragment= LibraryFragment()
        val accountCenterFragment= AccountCenterFragment()
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setCurrentFragment(homePageFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.button_home->setCurrentFragment(homePageFragment)
                R.id.button_create->setCurrentFragment(createFragment)
                R.id.button_library->setCurrentFragment(libraryFragment)
                R.id.button_account->setCurrentFragment(accountCenterFragment)

            }
            true
        }

    }


    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,fragment)
            commit()
        }
}