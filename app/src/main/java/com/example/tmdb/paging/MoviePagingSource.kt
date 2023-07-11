package com.example.tmdb.paging

import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdb.api.MovieApi
import com.example.tmdb.model.Movie

class MoviePagingSource(private val movieApi: MovieApi):PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {

            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        try {

            val position  = params.key ?:1
            val response = movieApi.getMovies(page = position)

            Log.d("Index","MSG ${position}")

            return LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position==1) null else position-1,
                nextKey = if (response.body()!!.results.isEmpty()) null else position+1

            )








        }catch (e:Exception){


            return LoadResult.Error(e)


        }
    }


}