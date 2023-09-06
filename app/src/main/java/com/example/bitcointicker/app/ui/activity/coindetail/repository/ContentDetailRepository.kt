package com.example.bitcointicker.app.ui.activity.coindetail.repository

import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import com.example.bitcointicker.data.request.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentDetailRepository @Inject constructor(
    private val networkService: NetworkService,
) {

    suspend fun coinDetailFetchData(id: String): Flow<DataFetchResult<CoinDetailResponseDTO>> =
        flow {
            val data =
                networkService.getCoinDetail(id = id)
            emit(DataFetchResult.Success(data))
        }
}