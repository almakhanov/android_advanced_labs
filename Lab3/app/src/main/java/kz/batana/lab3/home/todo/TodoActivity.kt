package kz.batana.lab3.home.todo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_todo.*
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants
import kz.batana.lab3.home.HomeContract
import org.koin.android.ext.android.inject

class TodoActivity : AppCompatActivity() {

    private val repository: HomeContract.Repository by inject()

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val id = intent.getIntExtra(Constants.TODO_ID, 1)

        repository.getTodo(id)
                .subscribe(
                        {
                            toto_title.text = "title: "+it.title
                            toto_id.text = "id: "+it.id.toString()
                            toto_user_id.text = "user_id: "+it.userId.toString()
                            toto_comp.text = "completed: "+it.completed.toString()
                        },
                        {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        })

    }
}
