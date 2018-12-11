package kz.batana.finalandroid.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kz.batana.finalandroid.R
import kz.batana.finalandroid.add_contact.AddContactActivity
import kz.batana.finalandroid.core.entity.Contact
import kz.batana.finalandroid.core.entity.ContactGroup
import org.koin.android.ext.android.inject
import java.io.Serializable

class MainActivity : AppCompatActivity(), MainContract.View, MainAdapter.OnItemClickListener {


    override val presenter: MainContract.Presenter by inject()
    private var actionbar: ActionBar? = null
    private var groupList = ArrayList<ContactGroup>()
    private lateinit var mainAdapter: MainAdapter
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

//        insertGroups()
        presenter.getGroups()
        presenter.getContact()

        fab.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }

    private fun insertGroups() {
        val list = ArrayList<ContactGroup>()
        list.apply {
            add(ContactGroup(0, "Family", 5))
            add(ContactGroup(0, "Relatives", 4))
            add(ContactGroup(0, "Friends", 3))
            add(ContactGroup(0, "Classmates", 2))
            add(ContactGroup(0, "None", 1))
        }
        presenter.insertGroup(list)
    }

    override fun onRestart() {
        super.onRestart()
        presenter.getContact()
    }

    override fun setContacts(arrayList: ArrayList<Contact>) {
        val layout = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        mainRec?.layoutManager = layout
        mainAdapter = MainAdapter(arrayList, this)
        mainRec?.adapter = mainAdapter
    }

    override fun onCourseClicked(course: Contact) {
        val tmpIntent = Intent(this, DeatilsActivity::class.java)
        tmpIntent.putExtra("key", course as Serializable)
        startActivity(tmpIntent)
    }

    override fun msg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setGroups(list: ArrayList<ContactGroup>) {
        groupList = list
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
