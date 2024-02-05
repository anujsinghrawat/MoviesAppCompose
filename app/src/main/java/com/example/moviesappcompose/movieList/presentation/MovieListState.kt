package com.example.moviesappcompose.movieList.presentation

import com.example.moviesappcompose.movieList.domain.model.Movie

data class MovieListState (
    val isLoading :Boolean = false,

    var popularMovieListPage:Int =1,
    var upcomingMovieListPage:Int =1,

    val isCurrentPopularScreen :Boolean = true,

    val popularMovieList:List<Movie> = emptyList(),
    val upcomingMovieList:List<Movie> = emptyList(),
 )