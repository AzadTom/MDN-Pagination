package com.example.tmdb

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.imageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.example.tmdb.databinding.ActivityDetailBinding
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.model.Movie
import com.example.tmdb.util.Utils

class DetailActivity : AppCompatActivity() {


     private lateinit var binding: ActivityDetailBinding



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movie  = intent.getParcelableExtra<Movie>("movie")

        movie?.let {

            binding.heroimg.load("${Utils.IMAGE_BASE_URL}${movie.backdrop_path}"){
                crossfade(750)
                build()
            }
            binding.herotitle.text = movie.title
            binding.herooverview.text = movie.overview
            binding.herorelease.text = movie.release_date
            binding.heroadult.text = "Yes"
        }


    }
}