package com.example.bitcointicker.app.ui.activity.coindetail.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitcointicker.app.ui.activity.coindetail.repository.ContentDetailRepository
import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ContentDetailViewModel @Inject constructor(
    private val contentDetailRepository: ContentDetailRepository
) : ViewModel() {
    suspend fun getCoinDetail(id:String): Flow<DataFetchResult<CoinDetailResponseDTO>> =
        contentDetailRepository.coinDetailFetchData(id = id)
            .catch {
                    exception ->
                emit(DataFetchResult.Failure(exception))
            }
            .flowOn(Dispatchers.IO)

}