package com.example.cleanpexels.ui.fragment.categories

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cleanpexels.R
import com.example.cleanpexels.databinding.FragmentCategoriesBinding
import com.example.cleanpexels.utils.Status


/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    private lateinit var dataBinding: FragmentCategoriesBinding
    private lateinit var loadingDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_categories,
            container, false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val categoriesFragmentViewModel = ViewModelProvider(
            requireActivity(),
            CategoriesFragmentViewModelFactory(requireActivity().application)
        ).get(
            CategoriesFragmentViewModel::class.java
        )
        loadingDialog = Dialog(requireContext())

        val categoriesFragmentAdapter = CategoriesFragmentAdapter()
        dataBinding.categoriesRecyclerview.apply {

            layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )

            adapter = categoriesFragmentAdapter
        }

        lifecycleScope.launchWhenResumed {
            categoriesFragmentViewModel.searchCategories.observe(
                viewLifecycleOwner, {
                    when (it.status) {
                        Status.ERROR -> {
                            loadingDialog.dismiss()
                        }
                        Status.LOADING -> {

                            loadingDialog.setCancelable(false)
                            loadingDialog.setContentView(R.layout.loading_dialog_layout)
                            loadingDialog.window?.setBackgroundDrawableResource(
                                android.R.color.transparent
                            )
                            loadingDialog.show()
                        }
                        Status.SUCCESS -> {
                            loadingDialog.dismiss()
                            categoriesFragmentAdapter.submitList(it.data)
                        }
                    }
                })
        }

    }

}