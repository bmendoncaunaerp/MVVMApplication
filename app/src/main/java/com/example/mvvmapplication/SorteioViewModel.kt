package com.example.mvvmapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class SorteioViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow("-")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun onSorteioClicked() {
        val newNumber = Random.nextInt(0, 999999)
        _uiState.update {
            newNumber.toString()
        }
    }
}