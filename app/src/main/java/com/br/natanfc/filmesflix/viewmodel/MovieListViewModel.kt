package com.br.natanfc.filmesflix.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.model.Movie
import com.br.natanfc.filmesflix.repository.MovieRepository
import java.lang.Exception

class MovieListViewModel : ViewModel() {

    companion object{
        const val TAG = "MovieRepository"
    }

    private val movieRestApiTask = MovieRestApiTask()
    private val movieRepository = MovieRepository(movieRestApiTask)

    //Cria a Lista com controle de anteraçoes do LiveData
    private var _moviesList = MutableLiveData<List<Movie>>()

    //moviesList para modo somente leitura, pra encapsulado _moviesList na MovieListViewModel
    val moviesList: LiveData<List<Movie>>
    get() = _moviesList

    fun init(){
        getAllMovies()
    }

    private  fun getAllMovies(){
        Thread{
            try {
                _moviesList.postValue(movieRepository.getAllMovies())
            }catch (exception: Exception){
                Log.d(TAG,exception.message.toString())
            }
        }.start()
    }
}