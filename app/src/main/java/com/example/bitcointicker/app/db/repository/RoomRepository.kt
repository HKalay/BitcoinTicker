package com.example.bitcointicker.app.db.repository

import com.example.bitcointicker.app.db.dao.CoinDAO
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.database.CoinDbRoomDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val coinDAO: CoinDAO,
) {
    suspend fun getAllCoins(): List<CoinDbRoomDTO> = withContext(Dispatchers.IO) {
        coinDAO.getAllCoins()
    }

    suspend fun insertCoin(coinResponseDTO: CoinResponseDTO) {
        withContext(Dispatchers.IO)
        {
            coinDAO.insertCoin(
                coinDbRoomDTO = CoinDbRoomDTO(
                    coinId = coinResponseDTO.id,
                    coinResponseDTO = coinResponseDTO
                )
            )
        }
    }

    suspend fun getCoinWithId(id: String): CoinDbRoomDTO =
        withContext(Dispatchers.IO) {
            coinDAO.getCoinWithId(id = id)
        }

    suspend fun deleteCoin(coinDbRoomDTO: CoinDbRoomDTO) {
        withContext(Dispatchers.IO)
        {
            coinDAO.deleteCoin(id = coinDbRoomDTO.id)
        }
    }
}