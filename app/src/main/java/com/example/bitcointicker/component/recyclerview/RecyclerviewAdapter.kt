package com.example.bitcointicker.component.recyclerview

import com.example.bitcointicker.component.recyclerview.helper.DefaultDisplayItemComperator
import com.example.bitcointicker.component.recyclerview.helper.RecyclerViewAdapter

class RecyclerviewAdapter {

    fun getAdapter() = adapter

    private val adapter = RecyclerViewAdapter(
        itemComperator = DefaultDisplayItemComperator(),
        viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
        viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
    )
}