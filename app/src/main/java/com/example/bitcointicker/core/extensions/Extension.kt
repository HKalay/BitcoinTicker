package com.example.bitcointicker.core.extensions

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.bitcointicker.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.Serializable

fun Context.isNetworkConnected(): Boolean {
    val cm =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.goneAnimation() {
    if (visibility != View.GONE) {
        animate()
            .translationX(0F)
            .alpha(0.0f).duration = 500
        visibility = View.GONE
    }
}

fun View.visibleAnimation(
) {
    if (visibility != View.VISIBLE) {
        animate()
            .translationX(0F)
            .alpha(1.0f).duration = 500
        visibility = View.VISIBLE
    }
}

fun Context.showToastLong(message: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

fun Context.showToastShort(message: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun ImageView.loadImage(url: Drawable) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(url: Int) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(url: String) {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1200)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    Glide.with(this)
        .load(url)
        .skipMemoryCache(true)
        .placeholder(shimmerDrawable)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadImageCircle(url: String) {

    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1200)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    Glide.with(this)
        .load(url)
        .skipMemoryCache(true)
        .placeholder(shimmerDrawable)
        .transform(
            RoundedCornersTransformation(
                360,
                0,
                RoundedCornersTransformation.CornerType.ALL
            )
        )
        .apply(RequestOptions.circleCropTransform())
        .into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(
                    resources,
                    (resource as BitmapDrawable).bitmap
                )
                circularBitmapDrawable.isCircular = true
                setImageDrawable(circularBitmapDrawable)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // Resim yükleme işlemi tamamlandığında ne yapılacağını belirtin (isteğe bağlı)
            }
        })
}

fun Context.getAttrDrawable(image: Int): Drawable {
    val typedValue = TypedValue()
    theme.resolveAttribute(image, typedValue, true)
    return ContextCompat.getDrawable(this, typedValue.resourceId)!!
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun Context.showAlertDialog(message: String) {
    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
    builder.setTitle(this.resources.getString(R.string.warning))
    builder.setMessage(message)
    builder.setPositiveButton(R.string.ok) { dialog, _ ->
        dialog.dismiss()
    }

    val alertDialog = builder.create()

    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    alertDialog.show()
}

fun isEmailValid(email: String): Boolean {
    val pattern = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,4})+$".toRegex()
    return pattern.matches(email)
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableArrayList(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayList(key)
}

inline fun <reified T : Parcelable> Intent.parcelableArrayList(key: String): ArrayList<T>? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableArrayListExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getSerializableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}

