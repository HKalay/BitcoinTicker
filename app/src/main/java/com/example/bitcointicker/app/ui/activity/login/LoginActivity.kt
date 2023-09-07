package com.example.bitcointicker.app.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.signup.SignUpActivity
import com.example.bitcointicker.core.extensions.loadImage
import com.example.bitcointicker.core.extensions.loadImageCircle
import kotlinx.android.synthetic.main.activity_login.btnSignUp
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.imgShowHidePasswordLogin

class LoginActivity : BaseActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()

        imgShowHidePasswordLogin.isSelected = false
        initShowHidePassword()
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
            imgShowHidePasswordLogin.isSelected = !imgShowHidePasswordLogin.isSelected
            val inputType = if (imgShowHidePasswordLogin.isSelected) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassword.inputType = inputType
            initShowHidePassword()

        }
    }

    private fun initShowHidePassword() {
        val image = if (imgShowHidePasswordLogin.isSelected) {
            R.drawable.ic_show_password
        } else {
            R.drawable.ic_hide_password
        }
        imgShowHidePasswordLogin.loadImage(image)
    }
}