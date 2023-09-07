package com.example.bitcointicker.app.ui.fragment.myfavoritecoins

import android.content.Intent
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseFragment
import com.example.bitcointicker.app.ui.activity.coindetail.CoinDetailActivity
import com.example.bitcointicker.app.ui.fragment.myfavoritecoins.viewmodel.MyFavoriteCoinsViewModel
import com.example.bitcointicker.component.recyclerview.RecyclerviewAdapter
import com.example.bitcointicker.component.recyclerview.helper.DisplayItem
import com.example.bitcointicker.component.recyclerview.helper.setup
import com.example.bitcointicker.component.ui.coinitem.CoinItemDTO
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.core.extensions.visible
import com.example.bitcointicker.core.helpers.DatabeseHelper
import com.example.bitcointicker.core.intent.IntentPutData
import com.example.bitcointicker.core.netowrk.DataFetchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_favorite_coins.loadingProgressCoinsFavorite
import kotlinx.android.synthetic.main.fragment_my_favorite_coins.rvCoinFavoriteList
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyFavoriteCoinsFragment : BaseFragment() {

    @Inject
    lateinit var adapterPageList: RecyclerviewAdapter

    @Inject
    lateinit var databeseHelper: DatabeseHelper

    private val viewModel: MyFavoriteCoinsViewModel by activityViewModels()


    override val layoutResId = R.layout.fragment_my_favorite_coins

    override fun binds() {
        initClickListener()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getCoinList()
    }

    private fun setupRecyclerView() {
        rvCoinFavoriteList.setup(
            adapter = adapterPageList.getAdapter(),
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )
    }

    private fun initClickListener() {
        adapterPageList.getAdapter().itemViewClickListener =
            { view: View, item: DisplayItem, _: Int ->

                when (item) {
                    is CoinItemDTO -> {
                        if (view.id == R.id.rootItemCoinCard) {

                            getCoinDetail(id = item.coinResponseDTO.id)
                        }
                    }
                }
            }
    }

    private fun getCoinList() {
        visibleView(view = loadingProgressCoinsFavorite)
        viewLifecycleOwner.lifecycleScope.launch {
            databeseHelper.getFavoritesList { dataList ->
                if (dataList.isEmpty()) {
                    //TODO boş ise null layout göster
                    // TODO geri geldiğimiz zaman içeriklerin hepsi kaldırılmışsa içerik bulunamadı yazısı olsun.
                } else {
                    adapterPageList.getAdapter()
                        .updateAllItems(dataList)
                }
                visibleView(rvCoinFavoriteList)
            }
        }
    }

    private fun getCoinDetail(id: String) {
        loadingProgressCoinsFavorite.visible()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCoinDetail(id = id).collect { result ->
                when (result) {
                    is DataFetchResult.Failure -> {
                        loadingProgressCoinsFavorite.gone()
                        val message1 = resources.getString(R.string.something_went_wrong)
                        val message2 = resources.getString(R.string.or)
                        val message3 = resources.getString(R.string.no_internet)
                        val message = "$message1\n$message2\n$message3"
                        requireActivity().showAlertDialog(message)
                    }

                    is DataFetchResult.Progress -> {

                    }

                    is DataFetchResult.Success -> {
                        loadingProgressCoinsFavorite.gone()
                        val intent =
                            Intent(requireActivity(), CoinDetailActivity::class.java)
                        intent.putExtra(
                            IntentPutData.COIN_DETAIL.value,
                            result.data
                        )
                        startActivity(intent)
                        requireActivity().overridePendingTransition(
                            R.anim.fade_in,
                            R.anim.fade_out
                        )
                    }
                }
            }
        }
    }

    private fun visibleView(view: View) {
        loadingProgressCoinsFavorite.gone()
        rvCoinFavoriteList.gone()

        view.visible()
    }
}