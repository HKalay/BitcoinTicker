package com.example.bitcointicker.app.ui.activity.coindetail

import android.os.Bundle
import android.util.Log
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.core.intent.IntentPutData

class CoinDetailActivity : BaseActivity(R.layout.activity_coin_detail) {

    private var coinId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readIntent()

    }

    private fun readIntent() {
        intent?.let {
            coinId =
                it.getStringExtra(IntentPutData.COIN_ID.value).toString()
        }
    }
}