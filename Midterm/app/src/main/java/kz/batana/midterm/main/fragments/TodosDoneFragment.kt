package kz.batana.midterm.main.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_done.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.Todo
import kz.batana.midterm.R
import kz.batana.midterm.main.MainActivity
import kz.batana.midterm.main.MainContract
import java.io.Serializable


class TodosDoneFragment : Fragment(), TodosAdapter.TodoItemClicked {

    private var listener: MainContract.TodoDoneListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.getTodosDone()
        Logger.msg("accepted","*************")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainContract.TodoDoneListener) {
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
        Logger.msg("accepted","*************setTodoList")
        recycler_todos_done.layoutManager = LinearLayoutManager(context)
        recycler_todos_done.adapter = TodosAdapter(activity!!, newsList, this)
    }

    override fun onItemClicked(todo: Todo) {
        val intent = Intent(activity, TodoDetailsActivity::class.java)
        intent.putExtra(Constants.TODO, todo as Serializable)
        startActivity(intent)
        Logger.msg("accepted", "clicked -> $todo")
    }

    companion object {
        @JvmStatic
        fun newInstance() = TodosDoneFragment()
    }


}
