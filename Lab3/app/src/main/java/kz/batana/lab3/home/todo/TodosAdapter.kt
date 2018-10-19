package kz.batana.lab3.home.todo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todo.view.*
import kz.batana.lab3.R
import kz.batana.lab3.core.entity.Todo

class TodosAdapter(private val context : Context,
                   private val items : ArrayList<Todo>,
                   private val listener : TodoItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return TodoViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_todo, p0, false))
    }

    override fun getItemCount(): Int {
          return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val todo = items[p1]
        p0.itemView.todo_name.text = todo.title

        p0.itemView.setOnClickListener {
            listener.onItemClicked(todo)
        }
    }


    class TodoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    interface TodoItemClicked {
        fun onItemClicked (todo : Todo)
    }

}

