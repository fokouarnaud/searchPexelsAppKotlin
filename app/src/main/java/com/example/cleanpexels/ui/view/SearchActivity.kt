package com.example.cleanpexels.ui.view

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanpexels.R
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var editSearch: EditText
    private lateinit var closeImg: ImageView
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setEditSearchFocus()
        setBackClickListener()
        setupSimpleSearchList()
    }


    private fun setupSimpleSearchList() {
        listView = findViewById(R.id.lvSearch)
        listView.divider = null
        val lst: MutableList<String> = ArrayList()
        lst.add(getString(R.string.love))
        lst.add(getString(R.string.school))
        lst.add(getString(R.string.art_design))
        val adapter = ArrayAdapter(this, R.layout.item_simple_search, R.id.searchtvshow, lst)
        listView.adapter = adapter
    }

    private fun setBackClickListener() {
        closeImg = findViewById(R.id.menu_icon)
        closeImg.setOnClickListener {
            closeKeyboard()
            onBackPressed()
        }
    }

    private fun setEditSearchFocus() {
        editSearch = findViewById(R.id.eidtsearch)
    }
    private fun closeKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

}