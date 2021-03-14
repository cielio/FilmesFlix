package com.br.natanfc.filmesflix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.model.Movie

class MovieListViewModel : ViewModel() {

    private val listOfMovies = arrayListOf(
        Movie(
            id = 0,
            titulo = "Titanic",
            descricao = null,
            imagem = null,
            dataLancamento = null
        ),
        Movie(
            id = 1,
            titulo = "Central do brasil",
            descricao = null,
            imagem = null,
            dataLancamento = null
        )
    )
    //Cria a Lista com controle de anteraçoes do LiveData
    private var _moviesList = MutableLiveData<List<Movie>>()

    //moviesList para modo somente leitura, pra encapsulado _moviesList na MovieListViewModel
    val moviesList: LiveData<List<Movie>>
    get() = _moviesList

    fun init(){
        getAllMovies()
    }

    private  fun getAllMovies(){
        _moviesList.value = listOfMovies
    }
}