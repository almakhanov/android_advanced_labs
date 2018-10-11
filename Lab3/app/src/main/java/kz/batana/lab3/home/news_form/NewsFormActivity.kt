package kz.batana.lab3.home.news_form

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
import kz.batana.lab3.MainActivity
import kz.batana.lab3.R
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.News
import java.io.Serializable

class NewsFormActivity : AppCompatActivity() {

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
            this.title = "News Form"
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
        val content = edit_text_add_news_desc.text.toString()
        val date = edit_text_add_news_date.text.toString()
        val photoUrl =  edit_text_add_news_photo.text.toString()
        return when (item.itemId) {
            R.id.item_teacher_add_course -> {
                if(title.isEmpty() || content.isEmpty() || date.isEmpty() || photoUrl.isEmpty()){
                    message("Fill the all fields!")
                }else {
                    val resultIntent = Intent(this, MainActivity::class.java)
                    resultIntent.putExtra(Constants.NEWS, News(0,
                            title,
                            date,
                            content,
                            photoUrl) as Serializable)
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
