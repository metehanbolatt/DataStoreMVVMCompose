package com.metehanbolat.datastoremvvmcompose.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.metehanbolat.datastoremvvmcompose.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository,
    application: Application
): AndroidViewModel(application) {

    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getUserName(application.applicationContext).collect {
                _userName.value = it
            }
        }
    }

    suspend fun saveUserName(userName: String, context: Context) = dataStorePreferenceRepository.setUserName(userName, context)
}