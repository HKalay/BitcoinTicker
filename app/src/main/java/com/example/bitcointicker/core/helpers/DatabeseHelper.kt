package com.example.bitcointicker.core.helpers

import android.content.Context
import android.util.Log
import com.example.bitcointicker.R
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DatabeseHelper {
    fun getUserToken(): String {

        val user = FirebaseAuth.getInstance().currentUser

        var userToken = ""
        user?.getIdToken(true)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userToken = task.result?.token.toString()
                }
            }
        return userToken
    }

    data class LoginResult(val user: FirebaseUser?, val errorMessage: String?)


    fun loginIsSuccess(email: String, password: String): LoginResult? {
        var loginResult: LoginResult? = null
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loginResult = if (task.isSuccessful) {
                    LoginResult(user = auth.currentUser, errorMessage = "")
                } else {
                    LoginResult(user = null, errorMessage = task.exception?.localizedMessage)
                }
            }
        return loginResult
    }

    fun sendEmailVerification(user: FirebaseUser, context: Context) {
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    context.showAlertDialog(message = context.resources.getString(R.string.mail_send_again))
                } else {
                    context.showAlertDialog(message = task.exception?.localizedMessage.toString())
                }
            }
    }
}