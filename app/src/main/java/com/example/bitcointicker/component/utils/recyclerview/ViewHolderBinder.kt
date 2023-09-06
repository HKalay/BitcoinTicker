package com.example.bitcointicker.component.utils.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointicker.component.utils.recyclerview.DisplayItem

interface ViewHolderBinder {
    fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem)
}