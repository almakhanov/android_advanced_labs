package kz.batana.midterm.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.khanproject.Logger
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.User
import kz.batana.midterm.R
import kz.batana.midterm.main.MainActivity
import org.koin.android.ext.android.inject
import java.io.Serializable

class LoginActivity : AppCompatActivity(), AuthContract.View {

    override val presenter: AuthContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorRed)
        }

        button_login.setOnClickListener{
            Logger.msg("accepted", "clicked")
            presenter.login(email_value_login.text.toString(), password_value_login.text.toString())
        }

        button_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onSuccessLogin(user: User) {
        presenter.saveUserLocally(user)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.USER, user as Serializable)
        startActivity(intent)
        finish()
    }

    override fun onFailedLogin(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
