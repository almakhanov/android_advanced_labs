package kz.batana.midterm.main.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_todo_details.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.core.Constants.TODO
import kz.batana.lab3.core.entity.Todo
import kz.batana.midterm.R
import kz.batana.midterm.main.MainContract
import org.koin.android.ext.android.inject

class TodoDetailsActivity : AppCompatActivity() {

    private val repository: MainContract.Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)

        val news = intent.getSerializableExtra(TODO) as Todo
        Logger.msg("accepted", "----> news")
        text_view_todo_title.text = news.title
        text_view_todo_desc.text = news.desc

        if(news.done != 0) {
            button_done.visibility = Button.GONE
            button_done.isEnabled = false
        }

        button_done.setOnClickListener{
            repository.doneTodo(news).subscribe()
            onBackPressed()
        }

    }
}
