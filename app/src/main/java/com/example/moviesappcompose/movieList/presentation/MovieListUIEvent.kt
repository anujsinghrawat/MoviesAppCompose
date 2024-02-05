package com.example.moviesappcompose.movieList.presentation

sealed interface MovieListUIEvent {
    data class Paginate(val category:String):MovieListUIEvent
    data object Navigate : MovieListUIEvent
}