package com.example.bitcointicker.core.extensions

import android.Manifest
import android.os.Build
import androidx.fragment.app.FragmentActivity
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.coroutines.sendSuspend
import com.fondesa.kpermissions.extension.permissionsBuilder

suspend fun FragmentActivity.permissionPostNotificationAllGranted(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissionsBuilder(
            Manifest.permission.POST_NOTIFICATIONS
        ).build()
            .sendSuspend().allGranted()
    } else {
        return true
    }
}