package com.metehanbolat.datastoremvvmcompose.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferenceRepository() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SampleData")

    private val userNameDefault = ""

    companion object {
        val PREF_USERNAME = stringPreferencesKey("user_name")
        private var INSTANCE: DataStorePreferenceRepository? = null

        fun getInstance(): DataStorePreferenceRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreferenceRepository()
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun setUserName(userName: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[PREF_USERNAME] = userName
        }
    }

    fun getUserName(context: Context): Flow<String> {
        val getUserName = context.dataStore.data
            .map { preferences ->
                preferences[PREF_USERNAME] ?: userNameDefault
            }
        return getUserName
    }

}