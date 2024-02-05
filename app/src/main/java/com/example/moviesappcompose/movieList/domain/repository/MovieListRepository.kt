package com.example.moviesappcompose.movieList.domain.repository

import com.example.moviesappcompose.movieList.domain.model.Movie
import com.example.moviesappcompose.movieList.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMoviesList(
        forceFetchFromRemote: Boolean,
        category:String,
        page:Int
    ) :Flow<Resource<List<Movie>>>

    suspend fun getMovie(
        id:Int
    ):Flow<Resource<Movie>>
}