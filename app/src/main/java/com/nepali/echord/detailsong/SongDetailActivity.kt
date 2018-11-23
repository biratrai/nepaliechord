package com.nepali.echord.detailsong

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.nepali.echord.GlideApp
import com.nepali.echord.NepaliChordConstant.Companion.SONG_DETAIL_INTENT
import com.nepali.echord.R
import com.nepali.echord.model.Song
import com.nepali.echord.model.SongDetail
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_song_detail.*
import org.jetbrains.anko.AnkoLogger

class SongDetailActivity : AppCompatActivity(), SongDetailContract.SongDetailView, AnkoLogger {
    private var presenter: SongDetailPresenter = SongDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song: Song = intent.extras.get(SONG_DETAIL_INTENT) as Song
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        setSupportActionBar(activitySongToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter.fetchSongDetail(song.songId!!)

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

    override fun displaySongDetail(songDetail: SongDetail) {
        if (songDetail.songLyrics != "")
            webView.loadDataWithBaseURL("", songDetail.songLyrics, "text/html", "utf-8", "")
        else
            webView.loadUrl("file:///android_asset/song.html")
    }
}
