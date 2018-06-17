package com.example.gooner10.nepaliechord.detailsong

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song = intent.getParcelableExtra<Song>("Song")
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/song.html")

        favorite_icon_detail.setOnClickListener { v: View? ->

            Log.i(TAG, "Clicked "+ song.isFavorite)
            if (song.isFavorite) {
                favorite_icon_detail.setBackground(getDrawable(R.drawable.ic_favorite_border_black_24dp))
                song.isFavorite = false
            } else {
                song.isFavorite = true
                favorite_icon_detail.setBackground(getDrawable(R.drawable.ic_favorite_black_24dp))
            }
        }
    }

    companion object {
        private val TAG = SongDetailActivity::class.java.simpleName
    }
}
