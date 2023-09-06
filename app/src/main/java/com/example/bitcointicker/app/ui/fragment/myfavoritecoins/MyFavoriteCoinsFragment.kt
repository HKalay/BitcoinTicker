package com.example.bitcointicker.app.ui.fragment.myfavoritecoins

import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFavoriteCoinsFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    override val layoutResId = R.layout.fragment_my_favorite_coins

    override fun binds() {

    }
}