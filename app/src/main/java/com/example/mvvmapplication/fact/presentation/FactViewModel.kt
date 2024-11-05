package com.example.mvvmapplication.fact.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapplication.fact.data.FactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FactViewModel(
    private val factRepository: FactRepository
) : ViewModel() {


    // Expose screen UI state
    private val _uiState = MutableStateFlow("-")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun onSorteioClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val fact = factRepository.getFact()
            _uiState.update { fact.text }
        }
    }
}