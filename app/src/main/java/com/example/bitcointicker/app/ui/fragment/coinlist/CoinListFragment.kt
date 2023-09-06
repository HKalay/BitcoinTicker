package com.example.bitcointicker.app.ui.fragment.coinlist

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.app.ui.fragment.coinlist.viewmodel.CoinListViewModel
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import com.example.bitcointicker.core.netowrk.DataFetchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CoinListFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    private val viewModel: CoinListViewModel by activityViewModels()

    override val layoutResId = R.layout.fragment_coin_list

    override fun binds() {
        getCoinList()
    }

    private fun getCoinList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCoinList().collect { result ->
                when (result) {
                    is DataFetchResult.Failure -> {
                        //TODO hata durumunda progress gone olup bg de null yazısı gözükecek
                        Log.i("Merhaba_result", "hata")
                    }

                    is DataFetchResult.Progress -> {
                        //TODO progress visible olacak
                        Log.i("Merhaba_result", "hata")
                    }

                    is DataFetchResult.Success -> {
                        //TODO bg deki null yazısı gidip recylerview gelecek
                        Log.i("Merhaba_result", result.data.toString())
                    }
                }
            }
        }
    }
}