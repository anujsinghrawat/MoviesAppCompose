package com.example.moviesappcompose.movieDetail.presentation

import com.example.moviesappcompose.movieList.domain.model.Movie

data class DetailsState(
    var isLoading:Boolean = false,
    val movie: Movie? = null
)