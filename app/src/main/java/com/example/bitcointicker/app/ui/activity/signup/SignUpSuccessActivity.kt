package com.example.bitcointicker.app.ui.activity.signup

import android.os.Bundle
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up_success.llGoLoginPage

class SignUpSuccessActivity : BaseActivity(R.layout.activity_sign_up_success) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()
    }

    private fun initClickListener(){
        llGoLoginPage.setOnClickListener {
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            finish()
        }
    }
}