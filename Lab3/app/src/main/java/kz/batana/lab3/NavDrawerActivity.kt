package kz.batana.lab3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nav_drawer.*
import kz.batana.lab3.home.HomeFragment
import kz.batana.lab3.home.NewsFragment

class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var actionbar: ActionBar? = null
    private lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)

        //toolbar
        setSupportActionBar(toolbar_main)
        actionbar = supportActionBar

        //nav animation
        val toggle = ActionBarDrawerToggle(
                this, nav_main_drawer, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        nav_main_drawer.addDrawerListener(toggle)
        toggle.syncState()

        //navigation view
        navigation_view_student_main.setNavigationItemSelectedListener(this)

//        var headerView = navigation_view_student_main.getHeaderView(0)
//        var navUsername = headerView.findViewById<TextView>(R.id.text_view_student_navigation_header_email)

        //default page
        actionbar?.apply {
            this.title = resources.getString(R.string.app_name)
        }

//        homeFragment = HomeFragment.newInstance()
//        createFragment(homeFragment, R.id.container_main)
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_home -> {
                actionbar?.apply {
                    this.title = resources.getString(R.string.app_name)
                }
                homeFragment = HomeFragment.newInstance()
                createFragment(NewsFragment.newInstance(), R.id.container_main)
            }
            R.id.nav_fav -> { }
            R.id.nav_rate -> { }
            R.id.nav_more -> { }
            R.id.nav_settings -> {
                openAboutDialog()
            }
        }
        nav_main_drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                nav_main_drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (nav_main_drawer.isDrawerOpen(GravityCompat.START)) {
            nav_main_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun createFragment(fragment: Fragment, layoutContainer: Int) {
        supportFragmentManager.beginTransaction()
                .replace(layoutContainer, fragment)
                //.addToBackStack(null)
                .commit()
    }

    private fun openAboutDialog(){
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About")
        builder.setMessage("This is Lab3.\nMade by Nursultan Almakhanov\n2018\nVersion: 0.0.1")
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> msg("Thank you!")
            }
        }

        builder.setPositiveButton("OK", dialogClickListener)

        dialog = builder.create()
        dialog.show()
    }

    private fun msg(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
