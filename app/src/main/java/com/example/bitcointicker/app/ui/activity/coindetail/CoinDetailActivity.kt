package com.example.bitcointicker.app.ui.activity.coindetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SwitchCompat
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.core.extensions.loadImageCircle
import com.example.bitcointicker.core.extensions.parcelable
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.core.intent.IntentPutData
import com.example.bitcointicker.core.utils.BottomSheetScreenUtils
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_coin_detail.imgCoinImage
import kotlinx.android.synthetic.main.activity_coin_detail.llAddUpdateTime
import kotlinx.android.synthetic.main.activity_coin_detail.tvActivePassiveStatus
import kotlinx.android.synthetic.main.activity_coin_detail.tvCoinName
import kotlinx.android.synthetic.main.activity_coin_detail.tvDescription
import kotlinx.android.synthetic.main.activity_coin_detail.tvHashAlgorithm
import kotlinx.android.synthetic.main.activity_coin_detail.tvLatest24HoursChnage
import kotlinx.android.synthetic.main.activity_coin_detail.tvPageRefreshInterval
import kotlinx.android.synthetic.main.activity_coin_detail.tvPrice

class CoinDetailActivity : BaseActivity(R.layout.activity_coin_detail) {

    var coinId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readIntent()
        initClickListener()

        setupBottomSheetData()
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

                    coinId = intentData.id.toString()

                    imgCoinImage.loadImageCircle(url = intentData.image?.large.toString())

                    tvCoinName.text = intentData.name
                    tvPrice.text = intentData.market_data?.current_price?.usd.toString() + "$"

                    val latest24Hours =
                        intentData.market_data?.price_change_percentage_24h.toString().ifEmpty {
                            resources.getString(R.string.not_found_24_hours)
                        }
                    tvLatest24HoursChnage.text = latest24Hours

                    val hashAlgorithm = if (intentData.hashing_algorithm.isNullOrEmpty()) {
                        resources.getString(R.string.hash_algorithm_not_found)
                    } else {
                        intentData.hashing_algorithm
                    }

                    tvHashAlgorithm.text = hashAlgorithm

                    val description = if (intentData.description?.en.isNullOrEmpty()) {
                        resources.getString(R.string.description_not_found)
                    } else {
                        intentData.description?.en
                    }

                    tvDescription.text = description
                }
        }
    }

    private fun initClickListener() {
        llAddUpdateTime.setOnClickListener {
            showTimeForRenewalDialog()
        }
    }

    private fun showTimeForRenewalDialog() {
        val timeRenewal = BottomSheetDialog(this)
        val view = View.inflate(this, R.layout.bottom_sheet_coin_refresh_time, null)
        timeRenewal.setContentView(view)

        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        timeRenewal.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        timeRenewal.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val root: LinearLayoutCompat =
            timeRenewal.findViewById(R.id.rootParent)!!
        val swRenewalIsActive: SwitchCompat? = timeRenewal.findViewById(R.id.swRenewalActive)
        val etRenewal: AppCompatEditText? = timeRenewal.findViewById(R.id.etRefreshInterval)
        val btnYes: AppCompatButton? = timeRenewal.findViewById(R.id.btnRefreshIntervalYes)
        val btnNo: AppCompatButton? = timeRenewal.findViewById(R.id.btnRefreshIntervalNo)


        swRenewalIsActive?.isChecked = sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)
        etRenewal?.setText(sharedPrefManager.getIsCoinRefreshTime(coindId = coinId).toString())


        btnYes?.setOnClickListener {
            //todo shared preference bilgiler kayıt edilecek

            val input = etRenewal?.text?.split(" ", "").toString()

            if (etRenewal?.text.isNullOrEmpty() || etRenewal?.text.toString().toInt() == 0
            ) {
                showAlertDialog(resources.getString(R.string.second_not_empty))
                return@setOnClickListener
            }

            sharedPrefManager.setIsCoinRefreshIsActive(
                coindId = coinId,
                value = swRenewalIsActive?.isChecked!!
            )

            sharedPrefManager.setIsCoinRefreshTime(
                coindId = coinId,
                value = etRenewal?.text.toString().toInt()
            )
            timeRenewal.dismiss()
        }
        btnNo?.setOnClickListener {
            timeRenewal.dismiss()
        }
        timeRenewal.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        timeRenewal.show()

        timeRenewal.setOnDismissListener {
            setupBottomSheetData()
        }

        val mBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(root.parent as View)
        val screenUtils = BottomSheetScreenUtils(this)
        mBehavior.peekHeight = screenUtils.height
    }

    private fun setupBottomSheetData() {

        val textColor = if (sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)){
            Color.GREEN
        }else{
            Color.RED
        }

        val text = if (sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)){
            resources.getString(R.string.active)
        }else{
            resources.getString(R.string.passive)
        }

        tvActivePassiveStatus.setTextColor(textColor)
        tvActivePassiveStatus.text = text


        tvPageRefreshInterval?.text =
            sharedPrefManager.getIsCoinRefreshTime(coindId = coinId).toString()
    }
}