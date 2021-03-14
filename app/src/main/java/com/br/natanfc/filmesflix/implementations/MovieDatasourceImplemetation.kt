package com.br.natanfc.filmesflix.implementations

import android.util.Log
import com.br.natanfc.filmesflix.framework.api.MovieRestApiTask
import com.br.natanfc.filmesflix.data.MovieDataSource
import com.br.natanfc.filmesflix.domain.Movie

class MovieDatasourceImplemetation(private val movieRestApiTask: MovieRestApiTask): MovieDataSource {
    companion object{
        const val TAG = "MovieRepository"
    }

    private val movielist = arrayListOf<Movie>()

    override fun getAllMovies(): List<Movie> {
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