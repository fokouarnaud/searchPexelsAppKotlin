package com.example.myapplication.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsPhoto
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.utils.Resource

class MainViewModel(application: Application) : ViewModel() {

    var pexelsPhotosLiveData: LiveData<Resource<List<PexelsPhoto>>>
    private val instanceMainRepository: MainRepository =
        MainRepository.getInstance(application)

    init {
        pexelsPhotosLiveData = liveData {
            emit(Resource.loading(null))
            try {
                emit(Resource.success(
                    instanceMainRepository.searchPhotos().photos
                )
                )
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        application.applicationContext.getString(
                            R.string.erreur_chargement
                        ), null
                    )
                )
            }
        }
    }
}