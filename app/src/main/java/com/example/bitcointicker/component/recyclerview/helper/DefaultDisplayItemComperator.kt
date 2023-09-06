package com.example.bitcointicker.component.recyclerview.helper

class DefaultDisplayItemComperator : DisplayItemComperator {

    override fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

}