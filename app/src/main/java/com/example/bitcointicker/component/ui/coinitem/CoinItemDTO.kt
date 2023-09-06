package com.example.bitcointicker.component.ui.coinitem

import android.os.Parcelable
import com.example.bitcointicker.component.constant.RecyclerviewAdapterConstant
import kotlinx.android.parcel.Parcelize
import com.example.bitcointicker.core.utils.recyclerview.DisplayItem
import com.example.bitcointicker.data.coin.CoinDTO

@Parcelize
data class CoinItemDTO(
    var coinDTO: CoinDTO,
) : Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_COIN_ITEM)
