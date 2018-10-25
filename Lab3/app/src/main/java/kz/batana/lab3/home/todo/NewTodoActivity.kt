package kz.batana.lab3.home.todo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_todo.*
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.Post
import java.io.Serializable

class NewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
        //Toolbar
        setSupportActionBar(toolbar_add_todo)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.title = "Post Form"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_news_add, menu)
        return true
    }

    @SuppressLint("CheckResult")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = edit_text_add_todo_title.text.toString()
        val id = edit_text_add_todo_id.text.toString().toInt()
        val postId = edit_text_add_todo_user_id.text.toString().toInt()
        val email = edit_text_add_todo_email.text.toString()
        val body = edit_text_add_post_body.text.toString()
        return when (item.itemId) {
            R.id.item_teacher_add_course -> {
                if(title.isEmpty()){
                    message("Fill the all fields!")
                }else {
                    val resultIntent = Intent()
                    resultIntent.putExtra(Constants.POST, Post(
                            postId, id, title, email, body
                    ) as Serializable)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun message(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
