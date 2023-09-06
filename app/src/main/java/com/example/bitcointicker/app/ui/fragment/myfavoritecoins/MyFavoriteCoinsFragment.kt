package com.example.bitcointicker.app.ui.fragment.myfavoritecoins

import androidx.fragment.app.activityViewModels
import com.example.bitcointicker.R
import com.example.bitcointicker.app.AppViewModel
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.component.ui.RecyclerviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFavoriteCoinsFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    private val viewModel: AppViewModel by activityViewModels()

    override val layoutResId = R.layout.fragment_my_favorite_coins

    override fun binds() {

    }
}