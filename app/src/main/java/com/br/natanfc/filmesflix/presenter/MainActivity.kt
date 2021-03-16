package com.br.natanfc.filmesflix.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.br.natanfc.filmesflix.R
import com.br.natanfc.filmesflix.domain.Movie
import com.br.natanfc.filmesflix.framework.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var moviesList: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //movieListViewModel pega a referencia da ViewModelProvider pra manimpular o LiveData
        movieListViewModel = ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init()
        initObserver()
        loadingVisibility(true)
    }
    private fun initObserver(){
        //O Observe pega todas as alteraçoes q tiver no movelist e passa pra view
        movieListViewModel.moviesList.observe(this, { list ->
            if (list.isNotEmpty()){
                moviesList = list
                populateList(list)
                loadingVisibility(false)
            }
        })
    }
    private fun populateList(list: List<Movie>) {
        moviesListRV.hasFixedSize()
        moviesList = list
        moviesListRV.adapter = MoviesAdapter(list,this)
    }
    //Liga e desliga o progressBar
    private fun loadingVisibility(isLoading: Boolean){
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", moviesList[position]
        )
        startActivity(intent)
    }
}