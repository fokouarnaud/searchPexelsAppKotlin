package com.example.cleanpexels.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cleanpexels.R
import com.example.cleanpexels.databinding.ActivityMainBinding
import com.example.cleanpexels.ui.fragment.categories.CategoriesFragment
import com.example.cleanpexels.ui.fragment.discover.DiscoverFragment
import com.example.cleanpexels.ui.fragment.favorites.FavoritesFragment
import com.example.cleanpexels.ui.fragment.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var categoriesFragment: CategoriesFragment
    private lateinit var discoverFragment: DiscoverFragment
    private lateinit var favoritesFragment: FavoritesFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        addDataToViews()
        addActionOnViews()
    }

    private fun initialize() {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_main,
            null,
            false
        )
        setContentView(dataBinding.root)

        categoriesFragment = CategoriesFragment()
        discoverFragment = DiscoverFragment()
        favoritesFragment = FavoritesFragment()
        profileFragment = ProfileFragment()

    }

    private fun addDataToViews() {
        setCurrentFragment(discoverFragment)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_content_frame_layout, fragment).commit()
        }
    }

    private fun addActionOnViews() {
        dataBinding.mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.discover_item -> setCurrentFragment(discoverFragment)
                R.id.categories_item -> setCurrentFragment(categoriesFragment)
                R.id.favorites_item -> setCurrentFragment(favoritesFragment)
                R.id.profile_item -> setCurrentFragment(profileFragment)
            }
            true
        }
    }
}