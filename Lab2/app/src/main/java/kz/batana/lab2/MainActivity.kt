package kz.batana.lab2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movies : ArrayList<Movie>
    private var adapter: Adapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(savedInstanceState != null){
            movies = savedInstanceState.getSerializable("movies") as ArrayList<Movie>
        }else{
            movies = ArrayList()
        }

        recycler()

        button_add_movie.setOnClickListener {
            movies.add(Movie("Johnny Depp", "Pirates of the Caribbean", 0))
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putSerializable("movies", movies)
    }


    private fun recycler(){
        adapter = Adapter(movies)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}
