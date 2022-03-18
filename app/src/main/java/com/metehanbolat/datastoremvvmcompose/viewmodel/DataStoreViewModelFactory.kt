package com.metehanbolat.datastoremvvmcompose.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metehanbolat.datastoremvvmcompose.repository.DataStorePreferenceRepository

class DataStoreViewModelFactory(private val dataStorePreferenceRepository: DataStorePreferenceRepository, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoreViewModel::class.java)) {
            return DataStoreViewModel(dataStorePreferenceRepository, application) as T
        }
        throw IllegalStateException()
    }

}