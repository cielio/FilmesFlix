package com.br.natanfc.filmesflix.repository

import android.util.Log
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import com.br.natanfc.filmesflix.model.Movie

class MovieRepository(
    private val movieRestApiTask: MovieRestApiTask
) {
    companion object{
        const val TAG = "MovieRepository"
    }

    private val movielist = arrayListOf<Movie>()

    fun getAllMovies():List<Movie>{
        val request = movieRestApiTask.retrofitApi().getAllMovies().execute()
        if (request.isSuccessful) {
            request.body()?.let {
                movielist.addAll(it)
            }
        }
        else {
            request.errorBody()?.let {
                Log.d(TAG, it.toString())
            }
        }
        return movielist
    }
}