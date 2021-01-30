package com.example.myapplication.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.main.adapter.MainAdapter
import com.example.myapplication.ui.main.base.MainViewModelFactory
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.utils.Status


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        addDataToViews()
    }

    private fun initialize() {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.activity_main,
            null,
            false
        )
        setContentView(dataBinding.root)

        mainAdapter = MainAdapter()
        dataBinding.recyclerviewPhoto.layoutManager = GridLayoutManager(this, 2)
        dataBinding.recyclerviewPhoto.adapter = mainAdapter
    }

    private fun addDataToViews() {
        val mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(
            MainViewModel::class.java
        )
        lifecycleScope.launchWhenResumed {
            mainViewModel.pexelsPhotosLiveData.observe(this@MainActivity, {
                when (it.status) {
                    Status.ERROR -> {
                        loadingDialog.dismiss()
                    }

                    Status.LOADING -> {
                        loadingDialog = Dialog(this@MainActivity)
                        loadingDialog.setCancelable(false)
                        loadingDialog.setContentView(R.layout.loading_dialog_layout)
                        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        loadingDialog.show()
                    }

                    Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        mainAdapter.submitList(it.data)

                    }
                }
            })
        }
    }
}