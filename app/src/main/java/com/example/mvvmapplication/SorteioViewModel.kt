package com.example.mvvmapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class SorteioViewModel : ViewModel() {
    private val api = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RandomFactsApi::class.java)

    // Expose screen UI state
    private val _uiState = MutableStateFlow("-")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun onSorteioClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getUsers()
            if(response.isSuccessful) {
                val fact = response.body()
                _uiState.update { fact?.text ?: "" }
            }
        }
    }
}