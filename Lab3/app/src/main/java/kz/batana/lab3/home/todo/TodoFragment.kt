package kz.batana.lab3.home.todo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_todo.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.Todo
import org.koin.android.ext.android.inject


class TodoFragment : Fragment(), TodoContract.View, TodosAdapter.TodoItemClicked {

    override fun onItemClicked(todo: Todo) {
        Logger.msg("accepted", todo)
        val intent = Intent(activity, TodoActivity::class.java)
        intent.putExtra(Constants.TODO_ID, todo.id)
        startActivity(intent)
    }

    override val presenter: TodoContract.Presenter by inject()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.attachView(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getTodos()

        fab_add_todo.setOnClickListener{
            startActivityForResult(Intent(activity, NewTodoActivity::class.java), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                val todo = data?.getSerializableExtra(Constants.TODO) as Todo
//                presenter.addTodo(todo)
                msg(todo.toString())
            }
        }
    }

    override fun setTodoList(todoList: ArrayList<Todo>) {
        if (context?.resources?.configuration?.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            recycler_todo.layoutManager = GridLayoutManager(context,2)
        } else{
            recycler_todo.layoutManager = LinearLayoutManager(context)
        }
        recycler_todo.adapter = TodosAdapter(activity!!, todoList, this)
    }

    override fun msg(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TodoFragment()
    }
}
