package com.example.bitcointicker.app.ui.activity.home

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.FragmentContainerView
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.visible
import kotlinx.android.synthetic.main.activity_home.bottomNavigation
import kotlinx.android.synthetic.main.activity_home.containerCoinList
import kotlinx.android.synthetic.main.activity_home.containerMyFavoriteCoins

private const val COINS_PAGE = "coins"
private const val FAVORITES_PAGE = "favorites_page"

class HomeActivity : BaseActivity(R.layout.activity_home) {

    private var selectedItem = R.id.navigation_coins
    private var clickedPage = COINS_PAGE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFragment(fragmentContainerView = containerCoinList)
        bottomNavigationClick()

        onBackPressedDispatcher.addCallback(this) {
            if (clickedPage == FAVORITES_PAGE) {
                bottomNavigation.selectedItemId = R.id.navigation_coins
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.selectedItemId = selectedItem
    }

    private fun showFragment(fragmentContainerView: FragmentContainerView) {
        containerCoinList.gone()
        containerMyFavoriteCoins.gone()

        fragmentContainerView.visible()
    }

    private fun bottomNavigationClick() {
        bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_coins -> {
                    clickedPage = COINS_PAGE
                    showFragment(fragmentContainerView = containerCoinList)
                }

                R.id.navigation_favorities -> {
                    clickedPage = FAVORITES_PAGE
                    showFragment(fragmentContainerView = containerMyFavoriteCoins)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}