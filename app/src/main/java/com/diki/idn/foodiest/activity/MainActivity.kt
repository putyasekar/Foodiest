package com.diki.idn.foodiest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.fragment.FavoriteFragment
import com.diki.idn.foodiest.fragment.HomeFragment
import com.diki.idn.foodiest.fragment.LocationFragment
import com.diki.idn.foodiest.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val homeFragment = HomeFragment()
                    addFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_location -> {
                    val locationFragment = LocationFragment()
                    addFragment(locationFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_fav -> {
                    val favoriteFragment = FavoriteFragment()
                    addFragment(favoriteFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_person -> {
                    val profileFragment = ProfileFragment()
                    addFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment::class.java.simpleName)
            .addToBackStack(null).commit()
    }

    private val defaultMainView = HomeFragment.defaultFragment() //memanggil default fragment, ini gaharus dimasukkin dalam onCreate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide() //cara hapus title bar selain di styles
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener) //attach bottom navigation
        addFragment(defaultMainView)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
