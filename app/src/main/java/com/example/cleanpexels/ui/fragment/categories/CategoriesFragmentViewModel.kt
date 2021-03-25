package com.example.cleanpexels.ui.fragment.categories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cleanpexels.R
import com.example.cleanpexels.data.model.SearchCategory
import com.example.cleanpexels.utils.Resource

class CategoriesFragmentViewModel(application: Application) : ViewModel() {


    private val _searchCategories: LiveData<Resource<List<SearchCategory>>>
    val searchCategories
        get() = _searchCategories

    init {
        _searchCategories = liveData {
            emit(Resource.loading(null))
            try {
                emit(
                    Resource.success(
                        getListSearchCategory(application)
                    )
                )
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        application.applicationContext.getString(
                            R.string.error_loading
                        ), null
                    )
                )
            }
        }
    }

    private fun getListSearchCategory(application: Application): List<SearchCategory> {
        val listCategory = listOf(
            Pair(
                application.applicationContext.getString(
                    R.string.art_design
                ),
                R.drawable.ic_round_design_services_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.augmented_reality
                ),
                R.drawable.ic_round_beach_access_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.beauty
                ),
                R.drawable.ic_round_loyalty_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.business
                ),
                R.drawable.ic_round_business_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.comics
                ),
                R.drawable.ic_round_bedroom_baby_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.communication
                ),
                R.drawable.ic_round_speaker_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.school
                ),
                R.drawable.ic_round_school_24
            ),
            Pair(
                application.applicationContext.getString(
                    R.string.love
                ),
                R.drawable.ic_round_people_24
            ),
        )
        val listSearchCategory: MutableList<SearchCategory> = mutableListOf()

        listCategory.forEach { targetCategory ->
            listSearchCategory.add(
                SearchCategory(
                    targetCategory.first,
                    targetCategory.second
                )
            )
        }
        return listSearchCategory
    }


}