package com.example.bitcointicker.app.db.repository

import com.example.bitcointicker.app.db.dao.CoinDAO
import com.example.bitcointicker.data.coin.CoinDTO
import com.example.bitcointicker.data.database.CoinDbDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val coinDAO: CoinDAO,
) {

    suspend fun getAllCoins(): List<CoinDbDTO> = withContext(Dispatchers.IO) {
        coinDAO.getAllCoins()
    }

    suspend fun insertCoin(coinDTO: CoinDTO) {
        withContext(Dispatchers.IO)
        {
            coinDAO.insertCoin(
                coinDbDTO = CoinDbDTO(
                    coinId = coinDTO.id,
                    coinDTO = coinDTO
                )
            )
        }
    }

    suspend fun deleteCoin(coinDbDTO: CoinDbDTO) {
        withContext(Dispatchers.IO)
        {
            coinDAO.deleteCoin(id = coinDbDTO.id)
        }
    }
}