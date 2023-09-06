package com.example.bitcointicker.app.ui.fragment.coinlist

import androidx.fragment.app.activityViewModels
import com.example.bitcointicker.R
import com.example.bitcointicker.app.AppViewModel
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.component.ui.RecyclerviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinListFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    private val viewModel: AppViewModel by activityViewModels()

    override val layoutResId = R.layout.fragment_coin_list

    override fun binds() {

    }
}