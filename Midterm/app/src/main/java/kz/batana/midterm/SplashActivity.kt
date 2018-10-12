package kz.batana.midterm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.khanproject.Logger
import kz.batana.lab3.auth.UserDao
import kz.batana.lab3.core.Constants
import kz.batana.lab3.core.local_storage.SharedPref
import kz.batana.midterm.auth.LoginActivity
import kz.batana.midterm.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent
import java.io.Serializable

@SuppressLint("Registered")
class SplashActivity : AppCompatActivity(), KoinComponent {

    private val pref: SharedPref by inject()
    private val roomUser: UserDao by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorRed)
        }

    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        roomUser.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    for(i in it){
                        Logger.msg("accepted", i)
                    }
                }
                .subscribe()


        if(!pref.getUserEmail().isNullOrEmpty() && !pref.getUserEmail().equals(Constants.EMPTY)){
            Logger.msg("accepted", "!isNullOrEmpty")
            roomUser.getUserByEmail(pref.getUserEmail()!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                if(it == null){
                                    Logger.msg("accepted", "ITs NulL")
                                }else{
                                    Logger.msg("accepted", "ITsNOT NulL $it")
                                }
                                if(it.password == pref.getUserPassword()){
                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.putExtra(Constants.USER, it as Serializable)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Logger.msg("accepted", "password incorrect")
                                    openLoginPage()
                                }
                            },
                            {
                                Logger.msg("accepted", it.message)
                                openLoginPage()
                            },
                            {
                                Logger.msg("accepted", "Do on Complete")
                            }
                    )
        }else{
            Logger.msg("accepted", "openLoginPage")
            openLoginPage()
        }
    }

    private fun openLoginPage(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
