package com.example.bitcointicker.app.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.isEmailValid
import com.example.bitcointicker.core.extensions.loadImage
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.core.extensions.visible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.btnSignUpCancel
import kotlinx.android.synthetic.main.activity_sign_up.etEmailSignUp
import kotlinx.android.synthetic.main.activity_sign_up.etPasswordAgain
import kotlinx.android.synthetic.main.activity_sign_up.etPasswordSignUp
import kotlinx.android.synthetic.main.activity_sign_up.imgShowHidePasswordAgainSignUp
import kotlinx.android.synthetic.main.activity_sign_up.imgShowHidePasswordSignUp
import kotlinx.android.synthetic.main.activity_sign_up.llSave
import kotlinx.android.synthetic.main.activity_sign_up.loadingProgressSignUp

class SignUpActivity : BaseActivity(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        imgShowHidePasswordSignUp.isSelected = false
        initShowHidePassword()

        imgShowHidePasswordAgainSignUp.isSelected = false
        initShowHidePasswordAgain()

        initClickListener()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun initClickListener() {
        btnSignUpCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        imgShowHidePasswordSignUp.setOnClickListener {
            imgShowHidePasswordSignUp.isSelected = !imgShowHidePasswordSignUp.isSelected
            val inputType = if (imgShowHidePasswordSignUp.isSelected) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPasswordSignUp.inputType = inputType
            initShowHidePassword()

        }

        imgShowHidePasswordAgainSignUp.setOnClickListener {
            imgShowHidePasswordAgainSignUp.isSelected = !imgShowHidePasswordAgainSignUp.isSelected
            val inputType = if (imgShowHidePasswordAgainSignUp.isSelected) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPasswordAgain.inputType = inputType
            initShowHidePasswordAgain()
        }

        llSave.setOnClickListener {
            if (etEmailSignUp.text.isNullOrEmpty() || etPasswordSignUp.text.isNullOrEmpty() || etPasswordAgain.text.isNullOrEmpty()) {
                showAlertDialog(message = resources.getString(R.string.all_fii_must_filled))
                return@setOnClickListener
            }

            if (!isEmailValid(email = etEmailSignUp.text.toString())) {
                showAlertDialog(message = resources.getString(R.string.valid_email_adress))
                return@setOnClickListener
            }

            if (!isEmailValid(email = etEmailSignUp.text.toString())) {
                showAlertDialog(message = resources.getString(R.string.valid_email_adress))
                return@setOnClickListener
            }

            if (etPasswordSignUp.text!!.length < 6) {
                showAlertDialog(message = resources.getString(R.string.pass_must_6_digits))
                return@setOnClickListener
            }

            if (etPasswordSignUp.text.toString() != etPasswordAgain.text.toString()) {
                showAlertDialog(message = resources.getString(R.string.password_not_match))
                return@setOnClickListener
            }

            signUp(
                email = etEmailSignUp.text.toString(), password = etPasswordSignUp.text.toString()
            )
        }
    }

    private fun signUp(email: String, password: String) {

        loadingProgressSignUp.visible()

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.sendEmailVerification()?.addOnCompleteListener { verificationTask ->
                    if (verificationTask.isSuccessful) {
                        loadingProgressSignUp.gone()
                        startActivity(Intent(this, SignUpSuccessActivity::class.java))

                        overridePendingTransition(
                            R.anim.fade_in, R.anim.fade_out
                        )
                        finish()
                    } else {
                        Log.i("Error_Message_1",verificationTask.exception.toString())
                        loadingProgressSignUp.gone()
                        showAlertDialog(message = verificationTask.exception.toString())
                    }
                }
            } else {
                Log.i("Error_Message_2",task.exception.toString())
                loadingProgressSignUp.gone()
                showAlertDialog(message = task.exception.toString())
            }
        }
    }

    private fun initShowHidePassword() {
        val image = if (imgShowHidePasswordSignUp.isSelected) {
            R.drawable.ic_show_password
        } else {
            R.drawable.ic_hide_password
        }
        imgShowHidePasswordSignUp.loadImage(image)
    }

    private fun initShowHidePasswordAgain() {
        val image = if (imgShowHidePasswordAgainSignUp.isSelected) {
            R.drawable.ic_show_password
        } else {
            R.drawable.ic_hide_password
        }
        imgShowHidePasswordAgainSignUp.loadImage(image)
    }
}