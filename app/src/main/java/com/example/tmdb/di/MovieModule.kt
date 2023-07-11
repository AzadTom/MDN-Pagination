package com.example.tmdb.di

import com.example.tmdb.api.MovieApi
import com.example.tmdb.repositery.MovieRepositery
import com.example.tmdb.util.Utils.BASE_URL
import com.example.tmdb.viewmodel.MovieViewmodel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object MovieModule {


    @Singleton
    @Provides
    fun providesRetrofitInstance() : Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }
    @Singleton
    @Provides
    fun  providesMovieApiInstance(retrofit: Retrofit):MovieApi{

        return retrofit.create(MovieApi::class.java)


    }


    @Singleton
    @Provides
    fun providesMovieRepositeryInstace(movieApi: MovieApi): MovieRepositery {

        return  MovieRepositery(movieApi)

    }


    @Singleton
    @Provides
    fun providesMovieviewmodelInstace(repositery: MovieRepositery): MovieViewmodel {

        return  MovieViewmodel(repositery)

    }



}