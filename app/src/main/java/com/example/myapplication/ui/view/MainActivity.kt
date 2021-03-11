package com.example.myapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.main.fragment.photos.PhotosFragment
import com.example.myapplication.ui.main.fragment.videos.VideosFragment


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var photosFragment: PhotosFragment
    private lateinit var videosFragment: VideosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        photosFragment = PhotosFragment()
        videosFragment = VideosFragment()

    }


    private fun addDataToViews() {
        setCurrentFragment(photosFragment)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_content, fragment).commit()
        }
    }

    private fun addActionOnViews() {
        dataBinding.bottomNavigationMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_photo_library -> setCurrentFragment(photosFragment)
                R.id.item_video_library -> setCurrentFragment(videosFragment)
            }
            true
        }
    }
}