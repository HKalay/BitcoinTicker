package com.example.bitcointicker.app.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.home.HomeActivity

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
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
            finish()
        }, 2000)

    }
}