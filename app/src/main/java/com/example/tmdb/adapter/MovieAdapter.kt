package com.example.tmdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tmdb.databinding.MovieItemBinding
import com.example.tmdb.model.Movie
import com.example.tmdb.util.Utils.IMAGE_BASE_URL

class MovieAdapter:PagingDataAdapter<Movie,MovieAdapter.MovieViewholder>(Comparator) {

    class MovieViewholder(itemview: MovieItemBinding) : RecyclerView.ViewHolder(itemview.root) {

         val  thumnail: ImageView = itemview.thumnail
         val  moviename: TextView = itemview.moviename

    }


    companion object{

        private val Comparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {

                return  oldItem == newItem
            }


        }


    }

    override fun onBindViewHolder(holder: MovieViewholder, position: Int) {


        val movie = getItem(position)

        movie?.let {


            holder.thumnail.load("${IMAGE_BASE_URL}${it.poster_path}")
            holder.moviename.text = it.title


        }






    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewholder {

        val movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewholder(movieItemBinding)

    }
}