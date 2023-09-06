package com.example.bitcointicker.app.ui.activity.coindetail

import android.os.Bundle
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.core.extensions.loadImageCircle
import com.example.bitcointicker.core.extensions.parcelable
import com.example.bitcointicker.core.intent.IntentPutData
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import kotlinx.android.synthetic.main.activity_coin_detail.imgCoinImage
import kotlinx.android.synthetic.main.activity_coin_detail.tvCoinName
import kotlinx.android.synthetic.main.activity_coin_detail.tvDescription
import kotlinx.android.synthetic.main.activity_coin_detail.tvHashAlgorithm
import kotlinx.android.synthetic.main.activity_coin_detail.tvLatest24HoursChnage
import kotlinx.android.synthetic.main.activity_coin_detail.tvPrice

class CoinDetailActivity : BaseActivity(R.layout.activity_coin_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readIntent()
        //TODO refresh time eklenecek
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun readIntent() {
        intent?.let {
            it.parcelable<CoinDetailResponseDTO>(IntentPutData.COIN_DETAIL.value)
                ?.let { intentData ->

                    imgCoinImage.loadImageCircle(url = intentData.image?.large.toString())

                    tvCoinName.text = intentData.name

                    tvPrice.text = intentData.market_data?.current_price?.usd.toString() + "$"
                    tvLatest24HoursChnage.text =
                        intentData.market_data?.price_change_percentage_24h.toString()

                    val hashAlgorithm = if (intentData.hashing_algorithm.isNullOrEmpty()){
                        resources.getString(R.string.hash_algorithm_not_found)
                    }else{
                        intentData.hashing_algorithm
                    }

                    tvHashAlgorithm.text = hashAlgorithm

                    val description = if (intentData.description?.en.isNullOrEmpty()){
                        resources.getString(R.string.description_not_found)
                    }else{
                        intentData.description?.en
                    }

                    tvDescription.text = description
                }
        }
    }
}