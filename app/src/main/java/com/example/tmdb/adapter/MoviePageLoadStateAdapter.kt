package com.example.tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R

class MoviePageLoadStateAdapter(private val retry:()->Unit): LoadStateAdapter<MoviePageLoadStateAdapter.LoaderViewHolder>() {



    inner  class LoaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val progressbar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val retrybtn = itemView.findViewById<Button>(R.id.retrybtn)
        val errorMSG = itemView.findViewById<TextView>(R.id.errorMSG)

        fun bind(loadState: LoadState){

            errorMSG.isVisible = loadState !is LoadState.Loading
            retrybtn.isVisible = loadState !is LoadState.Loading
            progressbar.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error)
            {

                errorMSG.visibility = View.VISIBLE
                retrybtn.visibility = View.VISIBLE
            }


            retrybtn.setOnClickListener {

                retry.invoke()

            }



        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {

        holder.bind(loadState)




    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {

        val view =  LayoutInflater.from(parent.context).inflate(R.layout.progressbar,parent,false)
        return  LoaderViewHolder(view)

    }


}