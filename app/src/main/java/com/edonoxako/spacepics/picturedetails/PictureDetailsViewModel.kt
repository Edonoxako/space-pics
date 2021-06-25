package com.edonoxako.spacepics.picturedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class PictureDetailsViewModel : ViewModel() {

    private val loadPictureDetailsUseCase by lazy {
        val repository = PictureDetailsRepository()
        LoadPictureDetailsUseCase(repository)
    }

    private val _pictureDetailsState = MutableLiveData<PictureDetailsState>(
        PictureDetailsState.Undefined
    )

    val pictureDetailsState: LiveData<PictureDetailsState>
        get() = _pictureDetailsState

    init {
        initializePictureDetailsState()
    }

    private fun initializePictureDetailsState() {
        if (_pictureDetailsState.value == PictureDetailsState.Undefined) {
            viewModelScope.launch {
                _pictureDetailsState.value = PictureDetailsState.InProgress
                val newState = loadPictureDetails()
                _pictureDetailsState.value = newState
            }
        }
    }

    private suspend fun loadPictureDetails(): PictureDetailsState {
        return try {
            val pictureDetails = loadPictureDetailsUseCase.execute()
            PictureDetailsState.Loaded(pictureDetails)
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error while loading picture details", e)
            PictureDetailsState.Error("Sorry, something is broken =(")
        }
    }

    companion object {
        private const val LOG_TAG = "PictureDetailsViewModel"
    }
}