package com.example.mvvmclean.anilshatharashi.presentation.ui

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowInsets
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mvvmclean.anilshatharashi.BuildConfig.BASE_URL_MOVIES_POSTERS
import com.example.mvvmclean.anilshatharashi.R

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(BASE_URL_MOVIES_POSTERS + url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun FragmentActivity.replaceFragment(fragment: Fragment, tag: String?) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment, tag)
        .addToBackStack(null)
        .commit()
}

fun FragmentActivity.addFragment(fragment: Fragment, tag: String?) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fragment, tag)
        .commit()
}

fun Activity.getSmallestWidthOfTheDevice(): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        return with(windowManager.currentWindowMetrics) {
            windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars()
                        or WindowInsets.Type.displayCutout()
            ).run {
                val widthDp = bounds.width() - (right + left)
                val heightDp = bounds.height() - (top + bottom)
                widthDp.coerceAtMost(heightDp)
            }
        }
    } else return with(DisplayMetrics()) {
        windowManager.defaultDisplay.getMetrics(this)
        val widthDp = widthPixels / density
        val heightDp = heightPixels / density
        Log.i("**", "======== = ")
        widthDp.coerceAtMost(heightDp).toInt()
    }
}