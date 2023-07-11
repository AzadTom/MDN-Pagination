package com.example.tmdb.repositery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdb.api.MovieApi
import com.example.tmdb.model.Movie
import com.example.tmdb.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepositery @Inject constructor(private val movieApi: MovieApi) {

    fun getMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { MoviePagingSource(movieApi) }
    ).flow

}