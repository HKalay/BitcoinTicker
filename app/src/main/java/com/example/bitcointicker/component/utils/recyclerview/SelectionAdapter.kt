package com.example.bitcointicker.component.utils.recyclerview

import com.example.bitcointicker.component.utils.recyclerview.DisplayItem

interface SelectionAdapter {
    fun select(pos: Int)
    fun clear()
    fun getSelectedItemCount(): Int
    fun getSelectedItems(): List<DisplayItem>
}