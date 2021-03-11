package com.example.myapplication.ui.main.fragment.photos

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotosBinding
import com.example.myapplication.ui.main.fragment.listPhotos.adapter.ListPhotosFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi


class PhotosFragment : Fragment(),
    SearchView.OnQueryTextListener {

    private lateinit var dataBinding: FragmentPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       // (activity as AppCompatActivity?)!!.supportActionBar!!.subtitle = "My App"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {


        inflater.inflate(R.menu.main_menu, menu);
        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView= searchItem?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled=true
        searchView?.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            showMessage("the query is: $query")
        }
        return true
    }

    private fun showMessage(msge: String?) {
        Toast.makeText(
            requireContext(),
            msge,
            Toast.LENGTH_LONG
        ).show()

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            showMessage("the nextText is: $newText")
        }
        return true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photos,
            container, false
        )

        return dataBinding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(dataBinding.toolBarPhotos)
        dataBinding.toolBarPhotos.title = "My App"
        addDataToViews()
    }

    @ExperimentalCoroutinesApi
    private fun addDataToViews() {
        dataBinding.viewPagerListItemCategoryPhoto.apply {
            adapter = ListPhotosFragmentAdapter(
                requireActivity()
            )
            isUserInputEnabled = false
        }



        TabLayoutMediator(
            dataBinding.tabListItemCategoryPhoto,
            dataBinding.viewPagerListItemCategoryPhoto
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.for_you)
                }
                1 -> {
                    tab.text = getString(R.string.top_orientation)
                }
                2 -> {
                    tab.text = getString(R.string.categories)
                }

            }
        }.attach()
    }
}