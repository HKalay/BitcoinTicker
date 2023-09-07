package com.example.bitcointicker.app.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.home.HomeActivity
import com.example.bitcointicker.app.ui.activity.login.LoginActivity
import com.example.bitcointicker.core.helpers.DatabeseHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    @Inject
    lateinit var databeseHelper: DatabeseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login()
    }

    private fun login() {
        val email = sharedPrefManager.getEmail()
        val password = sharedPrefManager.getPassword()

        if (email.isEmpty() || password.isEmpty()){
            goLoginActivity()
            return
        }else{
            if (databeseHelper.getUserToken().isEmpty()){
                goLoginActivity()
            }else{
                goHomeActivity()
            }
        }
    }

    private fun goHomeActivity(){
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            finish()
        }, 2000)
    }

    private fun goLoginActivity(){
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            finish()
        }, 2000)
    }
}

// TODO ana sayfada filtre yaptığımız zaman veri yoksa veri yok diye layout göstersin.
// TODO parcelize ile alakalı çıkan hatalar silinecek. DTOlara bakılacak
