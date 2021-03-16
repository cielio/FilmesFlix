package com.br.natanfc.filmesflix.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.natanfc.filmesflix.R
import com.br.natanfc.filmesflix.domain.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MoviesAdapter(
    private val moviesList: List<Movie>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MoviesViewHolder(view)
    }
    // passa o conteudo da Lista para a tela
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        //modo padrao
        //holder.itemView.movieTitle.text = moviesList[position].titulo
        //modo simplificado
        holder.itemView.apply {
            movieTitle.text = moviesList[position].titulo
            movieImage.load(moviesList[position].imagem){
                //imagem padrao antes de carregar a img da lista
                placeholder(R.drawable.ic_baseline_cloud_download)
                //caso a imagem na tenha na lista ou erro de load..
                fallback(R.drawable.ic_baseline_cloud_download)
            }
        }

    }

    override fun getItemCount(): Int = moviesList.size

    inner class MoviesViewHolder(

        itemView: View

    ) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}