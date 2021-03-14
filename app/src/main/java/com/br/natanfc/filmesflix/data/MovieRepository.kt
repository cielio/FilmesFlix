package com.br.natanfc.filmesflix.data

 class MovieRepository(
    private val movieDataSource:MovieDataSource
) {
    fun getAllMoviesFromDataSouce() = movieDataSource.getAllMovies()
}
