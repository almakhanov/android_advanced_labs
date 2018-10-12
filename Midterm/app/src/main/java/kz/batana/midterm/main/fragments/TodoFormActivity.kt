package kz.batana.midterm.main.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_news_form.*
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.Todo
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.midterm.R
import kz.batana.midterm.main.MainActivity
import org.koin.android.ext.android.inject
import java.io.Serializable

@SuppressLint("Registered")
class TodoFormActivity : AppCompatActivity() {

    private val pref: SharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_form)

        //Toolbar
        setSupportActionBar(toolbar_add_news)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            this.title = "Todo Form"
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
        val title = edit_text_add_news_title.text.toString()
        val desc = edit_text_add_news_desc.text.toString()
        return when (item.itemId) {
            R.id.item_teacher_add_course -> {
                if(title.isEmpty() || desc.isEmpty()){
                    message("Fill the all fields!")
                }else {
                    val resultIntent = Intent(this, MainActivity::class.java)
                    resultIntent.putExtra(Constants.TODO, Todo(0,
                            title,
                            desc,
                            0,
                            pref.getUserEmail()!!) as Serializable)
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
