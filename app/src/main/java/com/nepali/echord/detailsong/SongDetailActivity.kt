package com.nepali.echord.detailsong

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nepali.echord.NepaliChordConstant.Companion.SONG_DETAIL_INTENT
import com.nepali.echord.R
import com.nepali.echord.model.Song
import com.nepali.echord.model.SongDetail
import kotlinx.android.synthetic.main.song_detail_bottom_sheet.*
import kotlinx.android.synthetic.main.song_detail_main_content.*
import org.jetbrains.anko.AnkoLogger

class SongDetailActivity : AppCompatActivity(), SongDetailContract.SongDetailView, AnkoLogger {
    private var presenter: SongDetailPresenter = SongDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song: Song = intent.extras.get(SONG_DETAIL_INTENT) as Song
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        webView.setBackgroundColor(Color.TRANSPARENT)
//        setSupportActionBar(app_toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        hideSystemUI()
        presenter.fetchSongDetail(song.songId!!)

//        GlideApp.with(this).load(R.drawable.ic_account_circle_black_24dp)
//                .placeholder(R.drawable.ic_account_circle_black_24dp)
//                .error(R.drawable.ic_account_circle_black_24dp)
//                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(50, 2)))
//                .into(artist_image_icon_detail)

//        favorite_icon_detail.setOnClickListener { v: View? ->
//
//            Log.i(TAG, "Clicked " + song.isFavorite)
//            setSong(song)
//        }

//        favoriteFabIcon.setOnClickListener { view ->
//            Snackbar.make(view, "This is my favorite", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            setSong(song)
//        }
    }

    private fun setSong(song: Song) {
//        if (song.isFavorite) {
//            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border_black_24dp, null)
//            song.isFavorite = false
//        } else {
//            song.isFavorite = true
//            favoriteFabIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24dp, null)
//        }
    }

    override fun displaySongDetail(songDetail: SongDetail) {
        if (songDetail.songLyrics != "")
            webView.loadDataWithBaseURL("", songDetail.songLyrics, "text/html", "utf-8", "")
        else
            webView.loadUrl("file:///android_asset/song.html")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
