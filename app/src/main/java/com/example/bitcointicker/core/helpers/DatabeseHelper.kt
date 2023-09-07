package com.example.bitcointicker.core.helpers

import android.content.Context
import com.example.bitcointicker.R
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

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

    suspend fun loginIsSuccess(email: String, password: String, context: Context): LoginResult {
        return try {
            val auth = FirebaseAuth.getInstance()
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                LoginResult(user, "")
            } else {
                LoginResult(null, context.resources.getString(R.string.authentication_failed))
            }
        } catch (e: Exception) {
            LoginResult(null, e.localizedMessage)
        }
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