package kz.batana.lab2

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_movie_list.view.*

class Adapter(var movies: ArrayList<Movie>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.card_movie_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("accepted", "${movies.size}")
        return movies.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.name?.text = movies[p1].actorName
        p0.film?.text = movies[p1].filmName
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var name = itemView?.actor_name
        var film = itemView?.movie_name
    }
}