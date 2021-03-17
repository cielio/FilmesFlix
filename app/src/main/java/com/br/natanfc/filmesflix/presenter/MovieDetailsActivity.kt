package com.br.natanfc.filmesflix.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.br.natanfc.filmesflix.R
import com.br.natanfc.filmesflix.domain.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.extras?.getParcelable<Movie>("movie")

        tv_movie_title.text = movie?.titulo
        tv_movie_overview.text = movie?.descricao

        iv_movie_poster.load(movie?.imagem) {
            placeholder(R.drawable.ic_baseline_cloud_download)
            fallback(R.drawable.ic_baseline_cloud_download)
        }
    }
}