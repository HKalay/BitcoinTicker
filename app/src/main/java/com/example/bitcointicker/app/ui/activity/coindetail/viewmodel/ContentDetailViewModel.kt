package com.example.bitcointicker.app.ui.activity.coindetail.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.app.db.repository.RoomRepository
import com.example.bitcointicker.app.ui.fragment.coinlist.reposiroty.CoinRepository
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.database.CoinDbDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ContentDetailViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val _coinsStateFlow = MutableStateFlow<List<CoinDbDTO>>(emptyList())
    val coinsStateFlow: StateFlow<List<CoinDbDTO>> = _coinsStateFlow

    suspend fun getAllCoinsStateFlow() {
        withContext(Dispatchers.IO) {
            _coinsStateFlow.value = roomRepository.getAllCoins()
        }
    }

    suspend fun deleteCoins(coinDbDTO: CoinDbDTO) {
        roomRepository.deleteCoin(coinDbDTO = coinDbDTO)
        getAllCoinsStateFlow()

    }

    suspend fun insertCoins(coinResponseDTO: CoinResponseDTO) {
        roomRepository.insertCoin(coinResponseDTO = coinResponseDTO)
        getAllCoinsStateFlow()

    }
}