package com.example.bitcointicker.component.recyclerview

import com.example.bitcointicker.component.ui.coinitem.CoinItemViewHolder
import com.example.bitcointicker.component.ui.horizontalrecyclerview.HorizontalRecyclerViewHolder

class RecyclerviewAdapterConstant {
    object TYPES {
        const val TYPE_HORIZONTAL_RECYCLER = 0
        const val TYPE_COIN_ITEM = 0
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.BinderFactory(),
        TYPES.TYPE_COIN_ITEM to CoinItemViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.HolderFactory(),
        TYPES.TYPE_COIN_ITEM to CoinItemViewHolder.HolderFactory()
    )
}