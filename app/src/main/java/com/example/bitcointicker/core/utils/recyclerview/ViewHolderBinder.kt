package com.example.bitcointicker.core.utils.recyclerview

import androidx.recyclerview.widget.RecyclerView

interface ViewHolderBinder {
    fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem)
}