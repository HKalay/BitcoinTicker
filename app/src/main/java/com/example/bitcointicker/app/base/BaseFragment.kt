package com.example.bitcointicker.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.bitcointicker.core.utils.pref.SharedPrefManager
import javax.inject.Inject

@AndroidEntryPoint
abstract  class BaseFragment : Fragment(){

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutResId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            binds()
        }
    }

    abstract fun binds()
}