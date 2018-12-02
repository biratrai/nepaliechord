package com.nepali.echord.util

import android.os.Build
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.nepali.echord.GlideApp
import com.nepali.echord.R

inline fun favSongHandler() {
//        if (song.isFavorite) {
//            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border_black_24dp, null)
//            song.isFavorite = false
//        } else {
//            song.isFavorite = true
//            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24dp, null)
//        }
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadCircularImage(imageUrl: String) {
    GlideApp.with(this).load(imageUrl)
            .placeholder(R.drawable.ic_account_circle_black_24dp)
            .error(R.drawable.ic_account_circle_black_24dp)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}

fun makeStatusBarTranslucent(window: Window) {
    // Set Transparent StatusBar
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}