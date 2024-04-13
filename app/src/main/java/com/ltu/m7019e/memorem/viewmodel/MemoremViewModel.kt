package com.ltu.m7019e.memorem.viewmodel

import androidx.lifecycle.ViewModel
import com.ltu.m7019e.memorem.database.MemoremUiState
import com.ltu.m7019e.memorem.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemoremViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MemoremUiState())
    val uiState: StateFlow<MemoremUiState> = _uiState.asStateFlow()

    fun setSelectedMovie(movie: Movie) {
        _uiState.update { currentState ->
            currentState.copy(selectedMovie = movie)
        }
    }
}