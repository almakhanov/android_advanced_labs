package kz.batana.midterm.main.fragments

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todos.view.*
import kz.batana.lab3.core.entity.Todo
import kz.batana.midterm.R

class TodosAdapter(private val context : Context,
                   private val items : ArrayList<Todo>,
                   private val listener : TodoItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_todos, p0, false))
    }

    override fun getItemCount(): Int {
          return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val news = items[p1]
        p0.itemView.text_view_todo_title.text = news.title




        p0.itemView.setOnClickListener {
            listener.onItemClicked(news)
        }
    }


    class NewsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    interface TodoItemClicked {
        fun onItemClicked (todo : Todo)
        fun msg(message: String)
        fun setTodoList(newsList: ArrayList<Todo>)
    }

}

