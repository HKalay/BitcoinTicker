package com.example.bitcointicker.app.ui.fragment.coinlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.app.db.repository.RoomRepository
import com.example.bitcointicker.app.ui.fragment.coinlist.reposiroty.CoinListRepository
import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.data.coin.CoinDTO
import com.example.bitcointicker.data.coin.CoinListResponse
import com.example.bitcointicker.data.database.CoinDbDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val coinListRepository: CoinListRepository
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

    suspend fun insertCoins(coinDTO: CoinDTO) {
        roomRepository.insertCoin(coinDTO = coinDTO)
        getAllCoinsStateFlow()

    }

    suspend fun getCoinList(): Flow<DataFetchResult<CoinListResponse>> =
        coinListRepository.coinListFetchData()
            .catch { exception ->
                emit(DataFetchResult.Failure(exception))
            }
            .flowOn(Dispatchers.IO)
}