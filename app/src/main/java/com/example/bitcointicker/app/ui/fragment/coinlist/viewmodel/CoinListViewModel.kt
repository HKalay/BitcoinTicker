package com.example.bitcointicker.app.ui.fragment.coinlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.app.ui.fragment.coinlist.reposiroty.CoinRepository
import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    suspend fun getCoinList(): Flow<DataFetchResult<List<CoinResponseDTO>>> =
        coinRepository.coinListFetchData()
            .catch {
                    exception ->
                emit(DataFetchResult.Failure(exception))
            }
            .flowOn(Dispatchers.IO)

    suspend fun getCoinDetail(id:String): Flow<DataFetchResult<CoinDetailResponseDTO>> =
        coinRepository.coinDetailFetchData(id = id)
            .catch {
                    exception ->
                emit(DataFetchResult.Failure(exception))
            }
            .flowOn(Dispatchers.IO)
}