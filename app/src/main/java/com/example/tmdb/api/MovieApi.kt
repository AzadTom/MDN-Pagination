package com.example.tmdb.api

import com.example.tmdb.model.ResponseMovieList
import com.example.tmdb.util.Utils
import com.example.tmdb.util.Utils.API_KEY
import com.example.tmdb.util.Utils.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {


    @GET("movie/popular")
    suspend fun getMovies(
        @Query("language") language:String = "en-US",
        @Query("api_key") apiKey:String = API_KEY,
        @Query("page") page: Int =1
    ): Response<ResponseMovieList>


}