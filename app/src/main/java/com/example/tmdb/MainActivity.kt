package com.example.tmdb

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.adapter.MovieAdapter
import com.example.tmdb.adapter.MoviePageLoadStateAdapter
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.viewmodel.MovieViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    private lateinit var adapter: MovieAdapter
    private lateinit var headerOrFooter : MoviePageLoadStateAdapter

    @Inject
    lateinit var viewmodel: MovieViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        setUpRecyclerView()
        loadData()
        pullToRefresh()


    }


    private fun setUpRecyclerView() {

        adapter = MovieAdapter()
        headerOrFooter = MoviePageLoadStateAdapter { adapter.retry() }

        val concatAdapter  = adapter.withLoadStateFooter(
            footer = headerOrFooter
        )

        binding.recyclerview.adapter = concatAdapter
        loadData()

        val gridLayoutManager = GridLayoutManager(this,2)
        binding.recyclerview.layoutManager = gridLayoutManager

        gridLayoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {

                return  if (position == concatAdapter.itemCount -1 && headerOrFooter.itemCount > 0 ){

                    2
                }
                else
                {
                    1
                }
            }


        }






        adapter.addLoadStateListener { loadstate ->

            binding.recyclerview.isVisible = loadstate.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadstate.source.refresh is LoadState.Loading
            binding.loadingtitle.isVisible = loadstate.source.refresh is LoadState.Loading
            handlerror(loadstate)

        }


    }

    private fun pullToRefresh() {
        binding.swipeTorefresh.setOnRefreshListener {


            Handler().postDelayed({

                adapter.refresh()
                binding.swipeTorefresh.isRefreshing = false


            }, 2000)


        }
    }

    private fun loadData() {


        lifecycleScope.launchWhenCreated {


            viewmodel.getMovies.collectLatest {

                adapter.submitData(it)


            }

        }
    }

    private fun handlerror(loadstate: CombinedLoadStates) {


        val errorState = loadstate.source.append as? LoadState.Error
            ?: loadstate.source.prepend as? LoadState.Error

        errorState?.let {

            Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show()

        }
    }


}