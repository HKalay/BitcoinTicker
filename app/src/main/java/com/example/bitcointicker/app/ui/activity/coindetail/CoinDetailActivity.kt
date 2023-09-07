package com.example.bitcointicker.app.ui.activity.coindetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.lifecycleScope
import com.example.bitcointicker.R
import com.example.bitcointicker.app.base.BaseActivity
import com.example.bitcointicker.app.ui.activity.coindetail.viewmodel.ContentDetailViewModel
import com.example.bitcointicker.core.extensions.loadImageCircle
import com.example.bitcointicker.core.extensions.parcelable
import com.example.bitcointicker.core.extensions.showAlertDialog
import com.example.bitcointicker.core.intent.IntentPutData
import com.example.bitcointicker.core.netowrk.DataFetchResult
import com.example.bitcointicker.core.utils.BottomSheetScreenUtils
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_coin_detail.imgCoinImage
import kotlinx.android.synthetic.main.activity_coin_detail.llUpdateRefreshFrequency
import kotlinx.android.synthetic.main.activity_coin_detail.tvActivePassiveStatus
import kotlinx.android.synthetic.main.activity_coin_detail.tvCoinName
import kotlinx.android.synthetic.main.activity_coin_detail.tvDescription
import kotlinx.android.synthetic.main.activity_coin_detail.tvHashAlgorithm
import kotlinx.android.synthetic.main.activity_coin_detail.tvLatest24HoursChnage
import kotlinx.android.synthetic.main.activity_coin_detail.tvPageRefreshInterval
import kotlinx.android.synthetic.main.activity_coin_detail.tvPrice
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class CoinDetailActivity : BaseActivity(R.layout.activity_coin_detail) {

    private var coinId = ""
    private var scheduledExecutor: ScheduledExecutorService? = null
    private lateinit var coinDetailResponseDTO: CoinDetailResponseDTO

    private val viewModel: ContentDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readIntent()
        initClickListener()
        setupRenewalfrequencyStatus()
        refreshData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (scheduledExecutor != null)
            scheduledExecutor?.shutdown()
    }

    private fun readIntent() {
        intent?.let {
            it.parcelable<CoinDetailResponseDTO>(IntentPutData.COIN_DETAIL.value)
                ?.let { intentData ->
                    coinDetailResponseDTO = intentData
                    setupUI(coinDetailResponseDTO = intentData)
                }
        }
    }

    private fun setupUI(coinDetailResponseDTO: CoinDetailResponseDTO) {
        coinId = coinDetailResponseDTO.id.toString()

        imgCoinImage.loadImageCircle(url = coinDetailResponseDTO.image?.large.toString())

        tvCoinName.text = coinDetailResponseDTO.name
        tvPrice.text = coinDetailResponseDTO.market_data?.current_price?.usd.toString() + "$"

        val latest24Hours =
            coinDetailResponseDTO.market_data?.price_change_percentage_24h.toString().ifEmpty {
                resources.getString(R.string.not_found_24_hours)
            }
        tvLatest24HoursChnage.text = latest24Hours

        val hashAlgorithm = coinDetailResponseDTO.hashing_algorithm.toString().ifEmpty {
            resources.getString(R.string.hash_algorithm_not_found)
        }
        tvHashAlgorithm.text = hashAlgorithm

        val description = coinDetailResponseDTO.description?.en.toString().ifEmpty {
            resources.getString(R.string.description_not_found)
        }
        tvDescription.text = description
    }

    private fun initClickListener() {
        llUpdateRefreshFrequency.setOnClickListener {
            showRefreshFrequencyDialog()
        }

        /*imgFavorite.setOnClickListener {
            val database: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Favorites")
            val coinDbFirebaseRealtimeDTODTO = CoinDbFirebaseRealtimeDTO(
                coinId = coinId,
                coinDetailResponseDTO = coinDetailResponseDTO
            )
            database.child(coinId).setValue(coinDbFirebaseRealtimeDTODTO).addOnSuccessListener { Log.i("Merhaba","123123") }.addOnFailureListener {
                showAlertDialog(message = it.localizedMessage)
            }
        }*/
    }

    private fun showRefreshFrequencyDialog() {
        val renewalFrequencyDialog = BottomSheetDialog(this)
        val view = View.inflate(this, R.layout.bottom_sheet_dialog_coin_renewal_frequency, null)
        renewalFrequencyDialog.setContentView(view)

        (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        renewalFrequencyDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        renewalFrequencyDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val root: LinearLayoutCompat =
            renewalFrequencyDialog.findViewById(R.id.rootParent)!!
        val swRenewalFruquency: SwitchCompat? =
            renewalFrequencyDialog.findViewById(R.id.swItemRenewalFruquency)
        val etRenewalFrequencyTime: AppCompatEditText? =
            renewalFrequencyDialog.findViewById(R.id.etItemRenewalFrequencyTime)
        val btnRenewalFrequencyStatuSave: AppCompatButton? =
            renewalFrequencyDialog.findViewById(R.id.btnItemRenewalFrequencyStatuSave)
        val btnRenewalFrequencyStatuCancel: AppCompatButton? =
            renewalFrequencyDialog.findViewById(R.id.btnItemRenewalFrequencyStatuCancel)

        swRenewalFruquency?.isChecked = sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)
        etRenewalFrequencyTime?.setText(
            sharedPrefManager.getIsCoinRenewalFrequencyTime(coindId = coinId).toString()
        )

        btnRenewalFrequencyStatuSave?.setOnClickListener {
            if (etRenewalFrequencyTime?.text.isNullOrEmpty() || etRenewalFrequencyTime?.text.toString()
                    .toInt() == 0
            ) {
                showAlertDialog(resources.getString(R.string.second_not_empty))
                return@setOnClickListener
            }

            sharedPrefManager.setIsCoinRefreshIsActive(
                coindId = coinId,
                value = swRenewalFruquency?.isChecked!!
            )

            sharedPrefManager.setIsCoinRenewalFrequencyTime(
                coindId = coinId,
                value = etRenewalFrequencyTime?.text.toString().toInt()
            )
            renewalFrequencyDialog.dismiss()
        }

        btnRenewalFrequencyStatuCancel?.setOnClickListener {
            renewalFrequencyDialog.dismiss()
        }
        renewalFrequencyDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        renewalFrequencyDialog.show()

        renewalFrequencyDialog.setOnDismissListener {
            setupRenewalfrequencyStatus()
            refreshData()
        }

        val mBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(root.parent as View)
        val screenUtils = BottomSheetScreenUtils(this)
        mBehavior.peekHeight = screenUtils.height
    }

    private fun setupRenewalfrequencyStatus() {

        val textColor = if (sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)) {
            Color.GREEN
        } else {
            Color.RED
        }

        val text = if (sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)) {
            resources.getString(R.string.active)
        } else {
            resources.getString(R.string.passive)
        }

        tvActivePassiveStatus.setTextColor(textColor)
        tvActivePassiveStatus.text = text


        tvPageRefreshInterval?.text =
            sharedPrefManager.getIsCoinRenewalFrequencyTime(coindId = coinId).toString()
    }

    private fun getCoinDetail(id: String) {

        lifecycleScope.launch {
            viewModel.getCoinDetail(id = id).collect { result ->
                when (result) {
                    is DataFetchResult.Failure -> {

                    }

                    is DataFetchResult.Progress -> {

                    }

                    is DataFetchResult.Success -> {
                        setupUI(coinDetailResponseDTO = result.data)
                    }
                }
            }
        }
    }

    private fun refreshData() {

        if (scheduledExecutor != null)
            scheduledExecutor?.shutdown()

        if (!sharedPrefManager.getIsCoinRefreshIsActive(coindId = coinId)) {
            return
        }

        scheduledExecutor = Executors.newScheduledThreadPool(1)

        val refreshRunnable = Runnable {
            getCoinDetail(id = coinId)
        }

        val delay = sharedPrefManager.getIsCoinRenewalFrequencyTime(coindId = coinId).toLong()
        scheduledExecutor?.scheduleAtFixedRate(
            refreshRunnable,
            delay,
            delay,
            TimeUnit.SECONDS
        )
    }
}