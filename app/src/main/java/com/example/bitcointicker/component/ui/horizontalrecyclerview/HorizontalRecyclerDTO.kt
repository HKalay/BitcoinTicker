package com.example.bitcointicker.component.ui.horizontalrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapterConstant
import com.example.bitcointicker.component.recyclerview.helper.DisplayItem

@Parcelize
data class HorizontalRecyclerDTO(
    var horizontalRecyclerList: List<DisplayItem>,
    var isCircleLooping: Boolean = false
) : Parcelable, DisplayItem(RecyclerviewAdapterConstant.TYPES.TYPE_HORIZONTAL_RECYCLER)
