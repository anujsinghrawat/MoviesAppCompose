package com.example.moviesappcompose.movieList.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviesappcompose.movieList.presentation.MovieListState
import com.example.moviesappcompose.movieList.presentation.MovieListUIEvent
import com.example.moviesappcompose.movieList.presentation.components.MovieItemCard
import com.example.moviesappcompose.movieList.util.Category

@Composable
fun PopularMovieScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUIEvent) -> Unit
) {
    if (movieListState.popularMovieList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
//            CircularProgressIndicator()
            Text("Nothing Found")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.popularMovieList.size)
            {
                index ->
                MovieItemCard(
                    movie = movieListState.popularMovieList[index],
                    navHostController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))
                if(index >= movieListState.popularMovieList.size -1 && !movieListState.isLoading)
                {
                    onEvent(MovieListUIEvent.Paginate(Category.POPULAR))
                }

            }
        }
    }

}