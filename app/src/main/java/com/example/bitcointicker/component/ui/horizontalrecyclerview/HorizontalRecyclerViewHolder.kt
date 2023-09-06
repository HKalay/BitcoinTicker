package com.example.bitcointicker.component.ui.horizontalrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.example.bitcointicker.R
import com.example.bitcointicker.component.constant.RecyclerviewAdapterConstant
import com.example.bitcointicker.component.utils.setup
import com.example.bitcointicker.component.utils.recyclerview.DefaultDisplayItemComperator
import com.example.bitcointicker.component.utils.recyclerview.DisplayItem
import com.example.bitcointicker.component.utils.recyclerview.RecyclerViewAdapter
import com.example.bitcointicker.component.utils.recyclerview.ViewHolder
import com.example.bitcointicker.component.utils.recyclerview.ViewHolderBinder
import com.example.bitcointicker.component.utils.recyclerview.ViewHolderFactory
import javax.inject.Inject

class HorizontalRecyclerViewHolder(view: View) : ViewHolder<HorizontalRecyclerDTO>(view) {

	private var rcView: RecyclerView = view.findViewById(R.id.rvItemHorizontalRecyclerview)
	private val layoutManager =
		LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

	private var viewManager: RecyclerView.LayoutManager =
		LoopingLayoutManager(view.context, LoopingLayoutManager.HORIZONTAL, false)

	private var isAdapterSet = false

	private val adapter = RecyclerViewAdapter(
		itemComperator = DefaultDisplayItemComperator(),
		viewBinderFactoryMap = RecyclerviewAdapterConstant().binderFactoryMap,
		viewHolderFactoryMap = RecyclerviewAdapterConstant().holderFactoryMap
	)


	override fun bind(item: HorizontalRecyclerDTO) {

		ViewCompat.setNestedScrollingEnabled(rcView, false)
		rcView.setHasFixedSize(true)
		rcView.isNestedScrollingEnabled = false

		if (item.horizontalRecyclerList.isEmpty())
			return

		if (!adapter.hasObservers())
			adapter.setHasStableIds(true)

		if (!isAdapterSet) {
			isAdapterSet = true
			if (item.isCircleLooping) {
				rcView.setup(adapter, viewManager, true)
			} else {
				rcView.setup(adapter, layoutManager, true)
			}
		}

		rcView.setHasFixedSize(true)

		invoke()

		adapter.updateAllItems(item.horizontalRecyclerList)
	}

	override fun onAttachAdapter() {
	}

	override fun onDetachAdapter() {
	}

	class HolderFactory @Inject constructor() : ViewHolderFactory {
		override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
			val holder = HorizontalRecyclerViewHolder(
				LayoutInflater.from(parent.context).inflate(
					R.layout.item_layout_horizontal_recycler,
					parent,
					false
				)
			)
			holder.setIsRecyclable(false)
			return holder
		}
	}

	class BinderFactory @Inject constructor() : ViewHolderBinder {
		override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
			(holder as HorizontalRecyclerViewHolder).bind(item as HorizontalRecyclerDTO)
		}
	}

	private fun invoke(){
		adapter.itemClickListener = { _item, _position ->
			itemClickListener?.invoke(_item, _position)
		}
	}
}
