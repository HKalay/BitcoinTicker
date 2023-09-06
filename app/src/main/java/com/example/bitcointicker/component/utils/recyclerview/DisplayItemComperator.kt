package com.example.bitcointicker.component.utils.recyclerview

import com.example.bitcointicker.component.utils.recyclerview.DisplayItem

interface DisplayItemComperator {
    fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean

    fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean
}