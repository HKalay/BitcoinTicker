package com.example.bitcointicker.app.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.home.HomeActivity
import com.example.bitcointicker.app.ui.activity.login.LoginActivity
import com.example.bitcointicker.core.helpers.DatabeseHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    @Inject
    lateinit var databeseHelper: DatabeseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            login()
        }
    }

    private suspend fun login() {
        if (databeseHelper.getUserToken().isNullOrEmpty()) {
            goLoginActivity()
        } else {
            goHomeActivity()
        }
    }

    private fun goHomeActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            finish()
        }, 2000)
    }

    private fun goLoginActivity() {
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
