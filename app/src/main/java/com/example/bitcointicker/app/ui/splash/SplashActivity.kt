package com.example.bitcointicker.app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.HomeActivity
import com.example.bitcointicker.app.ui.login.LoginActivity

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity()
    }

    private fun startActivity() {
        //TODO daha önce giriş yapmışsa home page gidecek. Yapmamışsa login pagede olacak.
        Handler().postDelayed({
            if (sharedPrefManager.getIsLogin()) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)

    }
}