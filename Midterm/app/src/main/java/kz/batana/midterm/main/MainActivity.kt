package kz.batana.midterm.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kz.batana.khanproject.Logger
import kz.batana.midterm.R
import kz.batana.midterm.core.entity.Todo
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View {


    override val presenter: MainContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        presenter.getTodo(1)
    }

    override fun sendDataById(todo: Todo) {
        Logger.msg("accepted", todo)
    }

    override fun sendData(todoList: ArrayList<Todo>) {
        for(i in todoList){
            Logger.msg("accepted", i)
        }
    }

    override fun msg(msg: String) {
        Logger.msg("accepted", msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
