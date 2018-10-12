package kz.batana.midterm.main.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_news.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.Constants.TODO
import kz.batana.lab3.core.entity.Todo
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.midterm.R
import kz.batana.midterm.auth.LoginActivity
import kz.batana.midterm.main.MainActivity
import kz.batana.midterm.main.MainContract
import org.koin.android.ext.android.inject
import java.io.Serializable

class TodosFragment : Fragment(), TodosAdapter.TodoItemClicked {

    private var listener: MainContract.TodoListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.getTodos()
        fab_add_todo.setOnClickListener{ fab ->
            startActivityForResult(Intent(activity, TodoFormActivity::class.java), 1)
        }

        fab_sign_out.setOnClickListener{
            val pref: SharedPref by inject()
            pref.clearUserEmail()
            startActivity(Intent(activity, LoginActivity::class.java))
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainContract.TodoListener) {
            listener = context as MainActivity
        } else {
            throw RuntimeException(context.toString() + " must implement StudentListFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun msg(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setTodoList(newsList: ArrayList<Todo>) {
        recycler_todos_main.layoutManager = LinearLayoutManager(context)
        recycler_todos_main.adapter = TodosAdapter(activity!!, newsList, this)
    }

    override fun onItemClicked(todo: Todo) {
        val intent = Intent(activity, TodoDetailsActivity::class.java)
        intent.putExtra(TODO, todo as Serializable)
        startActivity(intent)
        Logger.msg("accepted", "clicked -> $todo")
    }

    companion object {
        @JvmStatic
        fun newInstance() = TodosFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                val news = data?.getSerializableExtra(Constants.TODO) as Todo
                listener?.addTodo(news)
            }
        }
    }





}
