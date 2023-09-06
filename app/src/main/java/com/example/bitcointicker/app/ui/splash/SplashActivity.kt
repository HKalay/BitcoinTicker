package com.example.bitcointicker.app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.HomeActivity

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity()
    }

    private fun startActivity() {
        Handler().postDelayed({
            //TODO daha önce giriş yapmışsa home page gidecek. Yapmamışsa login pagede olacak.
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 2000)

    }
}