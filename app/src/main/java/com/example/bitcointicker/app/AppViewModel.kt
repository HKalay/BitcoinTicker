package com.example.bitcointicker.app

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.app.db.repository.RoomRepository
import com.example.bitcointicker.data.coin.CoinDTO
import com.example.bitcointicker.data.database.CoinDbDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    private val _coinsStateFlow = MutableStateFlow<List<CoinDbDTO>>(emptyList())
    val coinsStateFlow: StateFlow<List<CoinDbDTO>> = _coinsStateFlow

    suspend fun getAllCoinsStateFlow() {
        withContext(Dispatchers.IO) {
            _coinsStateFlow.value = repository.getAllCoins()
        }
    }

    suspend fun deleteCoins(coinDbDTO: CoinDbDTO) {
        repository.deleteCoin(coinDbDTO = coinDbDTO)
        getAllCoinsStateFlow()

    }

    suspend fun insertCoins(coinDTO: CoinDTO) {
        repository.insertCoin(coinDTO = coinDTO)
        getAllCoinsStateFlow()

    }
}