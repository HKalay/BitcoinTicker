package com.example.bitcointicker.app.ui.activity.coindetail

import android.os.Bundle
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity

class CoinDetailActivity : BaseActivity(R.layout.activity_coin_detail) {

    private var coinId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readIntent()

    }

    private fun readIntent() {
        intent?.let {

        }
    }
}