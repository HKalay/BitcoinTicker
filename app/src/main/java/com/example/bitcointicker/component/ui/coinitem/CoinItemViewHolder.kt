package com.example.bitcointicker.component.ui.coinitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcointicker.R
import com.example.bitcointicker.core.utils.recyclerview.DisplayItem
import com.example.bitcointicker.core.utils.recyclerview.ViewHolder
import com.example.bitcointicker.core.utils.recyclerview.ViewHolderBinder
import com.example.bitcointicker.core.utils.recyclerview.ViewHolderFactory
import javax.inject.Inject

class CoinItemViewHolder(val view: View) : ViewHolder<CoinItemDTO>(view) {

    private var rootCoinCard: RelativeLayout =
        view.findViewById(R.id.rootItemCoinCard)
    private var tvCoinSymbol: AppCompatTextView =
        view.findViewById(R.id.tvItemCoinSymbol)
    private var tvItemCoinName: AppCompatTextView =
        view.findViewById(R.id.tvItemCoinName)

    override fun bind(item: CoinItemDTO) {
        invoke(item = item)
        bindData(item=item)
    }

    class HolderFactory @Inject constructor() : ViewHolderFactory {
        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CoinItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_coins,
                    parent,
                    false
                )
            )
    }

    class BinderFactory @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as CoinItemViewHolder).bind(item as CoinItemDTO)
        }
    }

    private fun invoke(item: CoinItemDTO) {
        rootCoinCard.setOnClickListener {
            itemViewClickListener?.invoke(it, item, adapterPosition)
        }
    }

    private fun bindData(item: CoinItemDTO){
        tvCoinSymbol.text = item.coinDTO.symbol
        tvItemCoinName.text = item.coinDTO.name
    }
}