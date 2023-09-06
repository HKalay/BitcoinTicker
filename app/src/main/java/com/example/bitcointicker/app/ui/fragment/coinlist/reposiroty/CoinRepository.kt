package com.example.bitcointicker.app.ui.fragment.coinlist.reposiroty

import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import com.example.bitcointicker.data.request.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val networkService: NetworkService,
) {
    suspend fun coinListFetchData(): Flow<DataFetchResult<List<CoinResponseDTO>>> =
        flow {
            val data =
                networkService.getCoinList()
            emit(DataFetchResult.Success(data))
        }

    suspend fun coinDetailFetchData(id: String): Flow<DataFetchResult<CoinDetailResponseDTO>> =
        flow {
            val data =
                networkService.getCoinDetail(id = id)
            emit(DataFetchResult.Success(data))
        }
}
