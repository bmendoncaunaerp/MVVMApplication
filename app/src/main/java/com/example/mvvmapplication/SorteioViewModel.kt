package com.example.mvvmapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlin.random.Random

class SorteioViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow("-")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    val networkJson = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/")
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()

    private val service: UselessFactService = retrofit.create(UselessFactService::class.java)

    fun onSorteioClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val fact = service.getRandomFact()
            withContext(Dispatchers.Main) {
                _uiState.update {
                    fact.text
                }
            }
        }
    }
}