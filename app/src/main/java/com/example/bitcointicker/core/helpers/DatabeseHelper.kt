package com.example.bitcointicker.core.helpers

import android.content.Context
import android.util.Log
import com.example.bitcointicker.R
import com.example.bitcointicker.component.ui.coinitem.CoinItemDTO
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import com.example.bitcointicker.data.database.CoinDbFirebaseRealtimeDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

class DatabeseHelper {

    val firebaseDbReferance = "Favorites"

    data class UserResult(val user: FirebaseUser?, val errorMessage: String?)

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

    suspend fun insertData(coinDetailResponseDTO: CoinDetailResponseDTO, context: Context) {
        val coinId = coinDetailResponseDTO.id.toString()
        if (childExistsData(childId = coinId)) {
            return
        }

        val database: DatabaseReference =
            FirebaseDatabase.getInstance().getReference(firebaseDbReferance)
        val coinDbFirebaseRealtimeDTODTO = CoinDbFirebaseRealtimeDTO(
            id = coinId,
            name = coinDetailResponseDTO.name.toString(),
            symbol = coinDetailResponseDTO.symbol
        )


        database.child(coinId).setValue(coinDbFirebaseRealtimeDTODTO)
            .addOnFailureListener {
                context.showAlertDialog(message = it.localizedMessage)
            }
    }

    fun deleteData(childId: String, context: Context) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(firebaseDbReferance)
        reference.child(childId).removeValue()
            .addOnFailureListener { e ->
                context.showAlertDialog(message = e.localizedMessage)
            }
    }

    suspend fun childExistsData(childId: String): Boolean {
        return try {
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference(firebaseDbReferance)
            val snapshot = reference.child(childId).get().await()
            return snapshot.exists()
        } catch (e: Exception) {
            return false
        }
    }

    fun getFavoritesList(onDataReceived: (List<CoinItemDTO>) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(firebaseDbReferance)

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataList = mutableListOf<CoinItemDTO>()
                for (childSnapshot in dataSnapshot.children) {

                    val myData = childSnapshot.getValue(CoinDbFirebaseRealtimeDTO::class.java)
                    if (myData != null) {
                        //dataList.add(myData)
                    }

                    Log.i("Merhaba",childSnapshot.value.toString())

                    //val gson = Gson()
                    //val myData = gson.fromJson(childSnapshot.value.toString(), CoinDbFirebaseRealtimeDTO::class.java)
                    /*val coinResponseDTO = CoinResponseDTO(
                        id = myData.id,
                        symbol = myData.symbol,
                        name = myData.name
                    )
                    dataList.add(CoinItemDTO(coinResponseDTO = coinResponseDTO))*/
                }
                onDataReceived(dataList)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}