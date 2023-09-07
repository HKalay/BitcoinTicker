package com.example.bitcointicker.core.helpers

import android.content.Context
import com.example.bitcointicker.R
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class DatabeseHelper {

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    suspend fun getUserToken(): String {
        return try {
            val user = FirebaseAuth.getInstance().currentUser
            val result = user?.getIdToken(true)?.await()
           return result?.token.toString()
        } catch (e: Exception) {
            ""
        }
    }

    data class UserResult(val user: FirebaseUser?, val errorMessage: String?)


    suspend fun createAccountIsSuccess(
        email: String,
        password: String,
        context: Context
    ): UserResult {
        return try {
            val auth = FirebaseAuth.getInstance()
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                UserResult(user, "")
            } else {
                UserResult(null, context.resources.getString(R.string.something_went_wrong))
            }
        } catch (e: Exception) {
            UserResult(null, e.localizedMessage)
        }
    }

    suspend fun loginIsSuccess(email: String, password: String, context: Context): UserResult {
        return try {
            val auth = FirebaseAuth.getInstance()
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                UserResult(user, "")
            } else {
                UserResult(null, context.resources.getString(R.string.authentication_failed))
            }
        } catch (e: Exception) {
            UserResult(null, e.localizedMessage)
        }
    }

    fun sendEmailVerification(user: FirebaseUser, context: Context, message: String) {
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    context.showAlertDialog(message = message)
                } else {
                    context.showAlertDialog(message = task.exception?.localizedMessage.toString())
                }
            }
    }
}