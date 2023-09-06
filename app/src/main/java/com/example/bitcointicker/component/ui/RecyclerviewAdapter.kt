package com.example.bitcointicker.component.ui

import com.example.bitcointicker.component.constant.RecyclerviewAdapterConstant
import com.example.bitcointicker.component.utils.recyclerview.DefaultDisplayItemComperator
import com.example.bitcointicker.component.utils.recyclerview.RecyclerViewAdapter

class RecyclerviewAdapter {

    fun getAdapter() = adapter

    private val adapter = RecyclerViewAdapter(
        itemComperator = DefaultDisplayItemComperator(),
        viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
        viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
    )
}