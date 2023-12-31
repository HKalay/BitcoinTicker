package com.example.bitcointicker.app.ui.activity.login

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.home.HomeActivity
import com.example.bitcointicker.app.ui.activity.signup.SignUpActivity
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.isEmailValid
import com.example.bitcointicker.core.extensions.loadImage
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.core.extensions.visible
import com.example.bitcointicker.core.helpers.DatabeseHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.btnSignUp
import kotlinx.android.synthetic.main.activity_login.etEmail
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.imgShowHidePasswordLogin
import kotlinx.android.synthetic.main.activity_login.llSignIn
import kotlinx.android.synthetic.main.activity_login.loadingProgressLogin
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity(R.layout.activity_login) {

    @Inject
    lateinit var databeseHelper: DatabeseHelper

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

            lifecycleScope.launch {
                signIn(email = etEmail.text.toString(), password = etPassword.text.toString())
            }
        }
    }

    private suspend fun signIn(email: String, password: String) {
        loadingProgressLogin.visible()

        val loginResult =
            databeseHelper.loginIsSuccess(email = email, password = password, context = this)
        val user = loginResult.user
        val message = loginResult.errorMessage


        if (user != null) {
            if (user.isEmailVerified) {
                loadingProgressLogin.gone()
                goHomeActivity()
            } else {
                loadingProgressLogin.gone()
                val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
                builder.setMessage(resources.getString(R.string.account_not_approved))
                builder.setPositiveButton(R.string.yes) { dialog, _ ->
                    dialog.dismiss()
                    databeseHelper.sendEmailVerification(
                        user = user,
                        context = this,
                        message = resources.getString(R.string.mail_send_again)
                    )
                }

                builder.setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = builder.create()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.show()
            }
        } else {
            loadingProgressLogin.gone()
            showAlertDialog(message.toString())
        }
    }

    private fun goHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
        finish()
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