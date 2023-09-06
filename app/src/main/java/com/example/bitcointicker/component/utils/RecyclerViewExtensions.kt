package com.example.bitcointicker.component.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointicker.component.utils.recyclerview.RecyclerViewAdapter

@SuppressLint("WrongConstant")
fun RecyclerView.setup(
    adapter: RecyclerViewAdapter,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context),
    isHasFixedSize: Boolean = false
) {
    this.layoutManager = layoutManager
    this.setHasFixedSize(isHasFixedSize)
    adapter.let {
        this.adapter = adapter
    }
}