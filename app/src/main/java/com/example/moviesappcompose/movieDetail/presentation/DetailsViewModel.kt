package com.example.moviesappcompose.movieDetail.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappcompose.movieList.domain.repository.MovieListRepository
import com.example.moviesappcompose.movieList.presentation.MovieListState
import com.example.moviesappcompose.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val movieId = savedStateHandle.get<Int>("movieId")


    private var _detailState = MutableStateFlow(DetailsState())
    val detailsState = _detailState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
        Log.e("TAG ", "getMovie: MovieId is $movieId ", )
    }

    private fun getMovie(id: Int) {
        Log.e("TAG ", "getMovie: MovieId is $movieId ", )

        viewModelScope.launch {
            _detailState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovie(id).collectLatest {
                result ->
                when(result)
                {
                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _detailState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let{
                            movie ->
                            _detailState.update{
                                it.copy(movie= movie)
                            }
                        }
                    }
                }
            }
        }
    }
}