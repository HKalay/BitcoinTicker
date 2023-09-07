package com.example.bitcointicker.app.ui.activity.signup

import android.os.Bundle
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_sign_up.btnSignUpCancel
import kotlinx.android.synthetic.main.activity_sign_up.etPasswordAgain
import kotlinx.android.synthetic.main.activity_sign_up.imgShowHidePasswordAgainSignUp
import kotlinx.android.synthetic.main.activity_sign_up.imgShowHidePasswordSignUp

class SignUpActivity : BaseActivity(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initClickListener()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun initClickListener(){
        btnSignUpCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        imgShowHidePasswordSignUp.setOnClickListener {

            //TODO burası düzenlecenecek resim değişmiyor.
            imgShowHidePasswordSignUp.isSelected = !imgShowHidePasswordSignUp.isSelected
            val inputType = if (imgShowHidePasswordSignUp.isSelected) {
                129
            } else {
                128
            }
            etPassword.inputType = inputType
        }

        imgShowHidePasswordAgainSignUp.setOnClickListener {

            //TODO burası düzenlecenecek resim değişmiyor.
            imgShowHidePasswordAgainSignUp.isSelected = !imgShowHidePasswordAgainSignUp.isSelected
            val inputType = if (imgShowHidePasswordAgainSignUp.isSelected) {
                129
            } else {
                128
            }
            etPasswordAgain.inputType = inputType
        }
    }
}