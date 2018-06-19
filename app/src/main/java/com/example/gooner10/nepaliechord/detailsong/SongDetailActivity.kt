package com.example.gooner10.nepaliechord.detailsong

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.example.gooner10.nepaliechord.GlideApp
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song = intent.getParcelableExtra<Song>("Song")
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/song.html")

        GlideApp.with(this).load(R.drawable.ic_account_circle_black_24dp)
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(50, 2)))
                .into(artist_image_icon_detail)

        favorite_icon_detail.setOnClickListener { v: View? ->

            Log.i(TAG, "Clicked " + song.isFavorite)
            setSong(song)
        }
    }

    private fun setSong(song: Song) {
        if (song.isFavorite) {
            favorite_icon_detail.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border_black_24dp, null)
            song.isFavorite = false
        } else {
            song.isFavorite = true
            favorite_icon_detail.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24dp, null)
        }
    }

    companion object {
        private val TAG = SongDetailActivity::class.java.simpleName
    }
}
