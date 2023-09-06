package com.example.bitcointicker.component.constant

import com.example.bitcointicker.component.ui.horizontalrecyclerview.HorizontalRecyclerViewHolder

class RecyclerviewAdapterConstant {
    object TYPES {
        const val TYPE_HORIZONTAL_RECYCLER = 0
    }

    var binderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.BinderFactory()
    )

    var holderFactoryMap = mutableMapOf(
        TYPES.TYPE_HORIZONTAL_RECYCLER to HorizontalRecyclerViewHolder.HolderFactory()
    )
}