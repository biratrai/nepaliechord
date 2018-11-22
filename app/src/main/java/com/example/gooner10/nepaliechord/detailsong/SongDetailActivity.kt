package com.example.gooner10.nepaliechord.detailsong

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.example.gooner10.nepaliechord.GlideApp
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.model.SongDetail
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_song_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SongDetailActivity : AppCompatActivity(), SongDetailContract.SongDetailView, AnkoLogger {
    private var presenter: SongDetailPresenter = SongDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song: Song = intent.extras.get("SongDetail") as Song
        info("song ${song.songId}")
        info("song ${song.songTitle}")
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        setSupportActionBar(activitySongToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter.fetchSongDetail(song.songId!!)
//        webView.loadUrl("file:///android_asset/song.html")
//        webView.loadDataWithBaseURL("", getString(R.string.html), "text/html", "utf-8", "")

        GlideApp.with(this).load(R.drawable.ic_account_circle_black_24dp)
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(50, 2)))
                .into(artist_image_icon_detail)

//        favorite_icon_detail.setOnClickListener { v: View? ->
//
//            Log.i(TAG, "Clicked " + song.isFavorite)
//            setSong(song)
//        }

        favoriteFabIcon.setOnClickListener { view ->
            Snackbar.make(view, "This is my favorite", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            setSong(song)
        }
    }

    private fun setSong(song: Song) {
        if (song.isFavorite) {
            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border_black_24dp, null)
            song.isFavorite = false
        } else {
            song.isFavorite = true
            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24dp, null)
        }
    }

    companion object {
        private val TAG = SongDetailActivity::class.java.simpleName
    }

    override fun displaySongDetail(songDetail: SongDetail) {
        if (songDetail.songLyrics != "")
            webView.loadUrl(songDetail.songLyrics)
        else
            webView.loadUrl("file:///android_asset/song.html")
    }
}
