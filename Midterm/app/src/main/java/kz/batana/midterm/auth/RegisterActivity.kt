package kz.batana.midterm.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.entity.User
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.midterm.R
import kz.batana.midterm.main.MainActivity
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private val userStorage: UserDao by inject()
    private val pref: SharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorRed)
        }

        button_sign_up.setOnClickListener{
            signUp(name_value_register.text.toString(), email_value_register.text.toString(),
                    password_value_register.text.toString())
        }
    }

    @SuppressLint("CheckResult")
    private fun signUp(name: String, email: String, password: String) {
        Observable.just(userStorage.insertUser(User(name, email, password)))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        {
                            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            pref.saveUserEmail(email)
                            pref.setUserPassword(password)
                            intent.putExtra(Constants.USER, User(name, email, password))
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        },
                        {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        })

    }



}
