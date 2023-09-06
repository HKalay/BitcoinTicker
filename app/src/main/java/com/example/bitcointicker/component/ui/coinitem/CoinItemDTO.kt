package com.example.bitcointicker.component.ui.coinitem

import android.os.Parcelable
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapterConstant
import kotlinx.android.parcel.Parcelize
import com.example.bitcointicker.component.recyclerview.helper.DisplayItem
import com.example.bitcointicker.data.coin.CoinResponseDTO

@Parcelize
data class CoinItemDTO(
    var coinResponseDTO: CoinResponseDTO,
) : Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_COIN_ITEM)
