package com.example.bitcointicker.app.ui.activity.splash

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.home.HomeActivity
import com.example.bitcointicker.app.ui.activity.login.LoginActivity
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login()
    }

    private fun login() {
        val email = sharedPrefManager.getEmail()
        val password = sharedPrefManager.getPassword()

        if (email.isEmpty() || password.isEmpty()){
            goLoginActivity()
            return
        }else{
            FirebaseAuth.getInstance().signOut()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            if (user.isEmailVerified) {
                                sharedPrefManager.setIsEmail(email = email)
                                sharedPrefManager.setIsPassword(password = password)
                                goHomeActivity()
                            } else {
                                goLoginActivity()
                            }
                        } else {
                            goLoginActivity()
                        }
                    } else {
                        val errorMessage = task.exception?.localizedMessage
                        goLoginActivity()
                    }
                }
        }
    }

    private fun goHomeActivity(){
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            finish()
        }, 2000)
    }

    private fun goLoginActivity(){
        FirebaseAuth.getInstance().signOut()
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

//TODO veri tabanına kayıt işlemi roomamı olacak firebasemi olacak.
// TODO sign yapılacak. Daha önce giriş yapılmışsa direk ana sayfaya atacak.
// TODO sign up yapıacal
// TODO Şifremi unuttum yapılacak. Mail onaylı olacak
// TODO sign up yaparken mail onayı olmalı
// TODO ana sayfada filtre yaptığımız zaman veri yoksa veri yok diye layout göstersin.
// TODO SignOut eklenecek. Signout edilen yerler mail ve şifre sıfırlanacak