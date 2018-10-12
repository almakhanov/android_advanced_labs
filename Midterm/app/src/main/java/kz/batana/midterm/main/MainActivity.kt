package kz.batana.midterm.main

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.core.entity.Todo
import kz.batana.midterm.R
import kz.batana.midterm.main.fragments.TodosDoneFragment
import kz.batana.midterm.main.fragments.TodosFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View, MainContract.TodoDoneListener, MainContract.TodoListener {

    override val presenter: MainContract.Presenter by inject()
    private var actionbar: ActionBar? = null
    private lateinit var todosFragment: TodosFragment
    private lateinit var doneFragment: TodosDoneFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)


        //toolbar
        setSupportActionBar(toolbar_main)
        actionbar = supportActionBar
        actionbar?.apply {
            this.title = resources.getString(R.string.app_name)
        }


        //View pager fragments
        val adapter = MainPagerAdapter(supportFragmentManager)
        todosFragment = TodosFragment.newInstance()
        doneFragment = TodosDoneFragment.newInstance()

        adapter.addFragment(todosFragment, resources.getString(R.string.tab_title1))
        adapter.addFragment(doneFragment, resources.getString(R.string.tab_title2))
        view_pager_home.adapter = adapter
        tab_layout_home.setupWithViewPager(view_pager_home)


    }

    override fun getTodosDone() {
        presenter.getDones()
    }

    override fun getTodos() {
        presenter.getTodos()
    }

    override fun addTodo(todo: Todo) {
        presenter.addTodo(todo)
    }

    override fun sendTodos(todoList: ArrayList<Todo>) {
        for(i in todoList){
            Logger.msg("accepted", i)
        }
        todosFragment.setTodoList(todoList)

    }

    override fun sendTodosDone(todoList: ArrayList<Todo>) {
        for(i in todoList){
            Logger.msg("accepted", "done --> $i")
        }
        doneFragment.setTodoList(todoList)
    }


    override fun msg(msg: String) {
        Logger.msg("accepted", msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
