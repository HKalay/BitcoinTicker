package com.example.bitcointicker.app.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.signup.SignUpActivity
import com.example.bitcointicker.core.extensions.isEmailValid
import com.example.bitcointicker.core.extensions.loadImage
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.btnSignUp
import kotlinx.android.synthetic.main.activity_login.etEmail
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.imgShowHidePasswordLogin
import kotlinx.android.synthetic.main.activity_login.llSignIn

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

        llSignIn.setOnClickListener {

            if (etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                showAlertDialog(message = resources.getString(R.string.all_fi_have_filled))
                return@setOnClickListener
            }

            if (!isEmailValid(email = etEmail.text.toString())) {
                showAlertDialog(message = resources.getString(R.string.valid_email_adress))
                return@setOnClickListener
            }

            if (etPassword.text!!.length < 6) {
                showAlertDialog(message = resources.getString(R.string.pass_must_6_digits))
                return@setOnClickListener
            }

            signIn()
        }
    }

    private fun signIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // Kullanıcı oturum açtı, ancak e-posta adresi onaylanmadı
            if (user.isEmailVerified) {
                // Kullanıcı oturum açtı ve e-posta adresi onaylandı
            } else {
                // Kullanıcı oturum açtı, ancak e-posta adresi onaylanmadı
                // E-posta onayı gönderme işlemine yönlendirin veya başka bir işlem yapın
            }
        } else {
            // Kullanıcı oturum açmamış
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