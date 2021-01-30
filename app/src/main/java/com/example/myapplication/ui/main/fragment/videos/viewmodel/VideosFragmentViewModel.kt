package com.example.myapplication.ui.main.fragment.videos.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsVideo
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.utils.Resource

class VideosFragmentViewModel(application: Application) : ViewModel() {

    var pexelsVideosLiveData: LiveData<Resource<List<PexelsVideo>>>
    private val instanceMainRepository: MainRepository =
        MainRepository.getInstance(application)

    init {
        pexelsVideosLiveData = liveData {
            emit(Resource.loading(null))
            try {
                emit(
                    Resource.success(
                        instanceMainRepository.searchVideos().videos
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