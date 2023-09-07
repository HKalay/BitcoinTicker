package com.example.bitcointicker.app.ui.activity.login

import android.content.Intent
import android.os.Bundle
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.btnSignUp
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.imgShowHidePasswordLogin

class LoginActivity : BaseActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
        }

        imgShowHidePasswordLogin.setOnClickListener {

            //TODO burası düzenlecenecek resim değişmiyor.
            imgShowHidePasswordLogin.isSelected = !imgShowHidePasswordLogin.isSelected
            val inputType = if (imgShowHidePasswordLogin.isSelected) {
                129
            } else {
                128
            }
            etPassword.inputType = inputType
        }
    }
}