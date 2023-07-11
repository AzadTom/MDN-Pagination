package com.example.tmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tmdb.repositery.MovieRepositery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(private val repositery: MovieRepositery):ViewModel() {


    val getMovies = repositery.getMovies().cachedIn(viewModelScope)

}