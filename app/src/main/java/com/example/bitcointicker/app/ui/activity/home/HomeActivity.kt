package com.example.bitcointicker.app.ui.activity.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.login.LoginActivity
import com.example.bitcointicker.core.extensions.gone
import com.example.bitcointicker.core.extensions.permissionPostNotificationAllGranted
import com.example.bitcointicker.core.extensions.visible
import com.example.bitcointicker.core.helpers.DatabeseHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.bottomNavigation
import kotlinx.android.synthetic.main.activity_home.containerCoinList
import kotlinx.android.synthetic.main.activity_home.containerMyFavoriteCoins
import kotlinx.android.synthetic.main.activity_home.imgAppExit
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val COINS_PAGE = "coins"
private const val FAVORITES_PAGE = "favorites_page"

@AndroidEntryPoint
class HomeActivity : BaseActivity(R.layout.activity_home) {

    @Inject
    lateinit var databeseHelper: DatabeseHelper

    private var selectedItem = R.id.navigation_coins
    private var clickedPage = COINS_PAGE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionRequired()
        showFragment(fragmentContainerView = containerCoinList)
        bottomNavigationClickListener()
        onBackPressedDispatcher()
        initClickListener()
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.selectedItemId = selectedItem
    }

    private fun permissionRequired() {
        lifecycleScope.launch {
            if (permissionPostNotificationAllGranted()) {
                return@launch
            }
        }
    }

    private fun onBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(this) {
            if (clickedPage == FAVORITES_PAGE) {
                bottomNavigation.selectedItemId = R.id.navigation_coins
            } else {
                showExitDialog()
            }
        }
    }

    private fun showFragment(fragmentContainerView: FragmentContainerView) {
        containerCoinList.gone()
        containerMyFavoriteCoins.gone()

        fragmentContainerView.visible()
    }

    private fun bottomNavigationClickListener() {
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

    private fun showExitDialog() {
        val exitDialog = BottomSheetDialog(this)
        val view = View.inflate(this, R.layout.bottom_sheet_dialog_exit, null)
        exitDialog.setContentView(view)
        val colorTransparent = ContextCompat.getColor(this, android.R.color.transparent)
        (view.parent as View).setBackgroundColor(colorTransparent)

        val btnYes: AppCompatButton? = exitDialog.findViewById(R.id.btnExitYes)
        val btnNo: AppCompatButton? = exitDialog.findViewById(R.id.btnExitNo)


        btnYes?.setOnClickListener {
            exitDialog.dismiss()
            finish()
        }
        btnNo?.setOnClickListener {
            exitDialog.dismiss()
        }
        exitDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        exitDialog.show()
    }

    private fun initClickListener() {
        imgAppExit.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
            builder.setMessage(resources.getString(R.string.exit_account_question))
            builder.setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                exitAccount()
            }

            builder.setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }
    }

    private fun exitAccount() {
        databeseHelper.signOut()
        sharedPrefManager.setIsPassword(password = "")
        sharedPrefManager.setIsEmail(email = "")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}