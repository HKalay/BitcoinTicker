package com.example.bitcointicker.app.ui.fragment.coinlist

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.app.ui.activity.coindetail.CoinDetailActivity
import com.example.bitcointicker.app.ui.fragment.coinlist.viewmodel.CoinListViewModel
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import com.example.bitcointicker.component.recyclerview.helper.DisplayItem
import com.example.bitcointicker.component.recyclerview.helper.setup
import com.example.bitcointicker.component.ui.coinitem.CoinItemDTO
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.visible
import com.example.bitcointicker.core.intent.IntentPutData
import com.example.bitcointicker.core.netowrk.DataFetchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_coin_list.errorView
import kotlinx.android.synthetic.main.fragment_coin_list.etSearch
import kotlinx.android.synthetic.main.fragment_coin_list.imgClearTextSearch
import kotlinx.android.synthetic.main.fragment_coin_list.loadingProgressCoins
import kotlinx.android.synthetic.main.fragment_coin_list.rvCoinList
import kotlinx.android.synthetic.main.fragment_coin_list.rvSuccessView
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CoinListFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    private var filtersIsActive = false

    private val viewModel: CoinListViewModel by activityViewModels()

    private val coinList = arrayListOf<CoinItemDTO>()

    override val layoutResId = R.layout.fragment_coin_list

    override fun binds() {

        initClickListener()
        setupRecyclerView()
        getCoinList()
        editTextWatchListener()
    }

    private fun setupRecyclerView() {
        rvCoinList.setup(
            adapter = adapterPageList.getAdapter(),
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )
    }

    private fun initClickListener() {
        imgClearTextSearch.setOnClickListener {
            etSearch.text?.clear()
        }

        adapterPageList.getAdapter().itemViewClickListener =
            { view: View, item: DisplayItem, _: Int ->

                when (item) {
                    is CoinItemDTO -> {
                        if (view.id == R.id.rootItemCoinCard) {
                            val intent = Intent(requireActivity(), CoinDetailActivity::class.java)
                            intent.putExtra(
                                IntentPutData.COIN_ID.value,
                                item.coinResponseDTO.id
                            )
                            startActivity(intent)
                        }
                    }
                }
            }
    }

    private fun getCoinList() {
        visibleView(view = loadingProgressCoins)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCoinList().collect { result ->
                when (result) {
                    is DataFetchResult.Failure -> {
                        visibleView(view = errorView)
                    }

                    is DataFetchResult.Progress -> {

                    }

                    is DataFetchResult.Success -> {
                        result.data.forEach {
                            coinList.add(element = CoinItemDTO(coinResponseDTO = it))
                        }

                        adapterPageList.getAdapter()
                            .updateAllItems(coinList)

                        visibleView(view = rvSuccessView)
                    }
                }
            }
        }
    }

    private fun editTextWatchListener() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isEmpty()) {
                    filtersIsActive = false
                    imgClearTextSearch.gone()
                } else {
                    filtersIsActive = true
                    imgClearTextSearch.visible()
                }
                filterList(etSearch.text.toString())
            }
        })
    }

    private fun filterList(text: String) {
        if (text.isNotEmpty()) {
            val filteredList: ArrayList<CoinItemDTO> = ArrayList()

            coinList.forEach {
                if (it.coinResponseDTO.name.lowercase().contains(text.lowercase())
                    || it.coinResponseDTO.symbol.lowercase().contains(text.lowercase())
                ) {
                    filteredList.add(it)
                }
                adapterPageList.getAdapter().updateAllItems(filteredList)
            }
        } else {
            adapterPageList.getAdapter().updateAllItems(coinList)
        }
    }


    private fun visibleView(view: View) {
        loadingProgressCoins.gone()
        rvSuccessView.gone()

        view.visible()
    }
}