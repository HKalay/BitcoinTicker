package com.example.bitcointicker.app.ui.fragment.coinlist

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.app.ui.fragment.coinlist.viewmodel.CoinListViewModel
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import com.example.bitcointicker.component.recyclerview.helper.setup
import com.example.bitcointicker.component.ui.coinitem.CoinItemDTO
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.visible
import com.example.bitcointicker.core.netowrk.DataFetchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_coin_list.loadingProgressCoins
import kotlinx.android.synthetic.main.fragment_coin_list.rvCoinList
import kotlinx.android.synthetic.main.fragment_coin_list.rvSuccessView
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CoinListFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    private val viewModel: CoinListViewModel by activityViewModels()

    private val coinList = arrayListOf<CoinItemDTO>()

    override val layoutResId = R.layout.fragment_coin_list

    override fun binds() {

        rvCoinList.setup(
            adapter = adapterPageList.getAdapter(),
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )

        getCoinList()
    }

    private fun getCoinList() {
        visibleView(view = loadingProgressCoins)
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
                        visibleView(view = rvSuccessView)

                        result.data.forEach{
                            coinList.add(element = CoinItemDTO(coinResponseDTO = it))
                        }

                        adapterPageList.getAdapter()
                            .updateAllItems(coinList)
                    }
                }
            }
        }
    }

    private fun visibleView(view:View){
        loadingProgressCoins.gone()
        rvSuccessView.gone()

        view.visible()
    }
}