package com.nepali.echord.detailsong

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.nepali.echord.NepaliChordConstant.Companion.SONG_DETAIL_INTENT
import com.nepali.echord.R
import com.nepali.echord.model.Song
import com.nepali.echord.model.SongDetail
import kotlinx.android.synthetic.main.activity_song_detail.*
import kotlinx.android.synthetic.main.song_detail_bottom_sheet.*
import kotlinx.android.synthetic.main.song_detail_main_content.*
import org.jetbrains.anko.AnkoLogger


class SongDetailActivity : AppCompatActivity(), SongDetailContract.SongDetailView, AnkoLogger {
    private var presenter: SongDetailPresenter = SongDetailPresenter(this)
    private val handler = Handler()
    private var speedMode = Speed.NORMAL
    private var currentSpeed = 300L
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song: Song = intent.extras.get(SONG_DETAIL_INTENT) as Song
        songTitleName.text = song.songTitle
        webView.settings.javaScriptEnabled = true
        webView.setBackgroundColor(ContextCompat.getColor(this, R.color.white_70))
        hideSystemUI()
        presenter.fetchSongDetail(song.songId!!)

        playFabIcon.setOnClickListener(playIconListener)

        speedFabIcon.setOnClickListener(speedIconListener)
    }

    /**
     * Listener that responds to the play FAB button press
     */
    private val playIconListener = View.OnClickListener { view: View? ->
        isPlaying = !isPlaying
        val backgroundImage: Int
        if (isPlaying) {
            backgroundImage = R.drawable.ic_pause_black_24dp
            scrollDown.run()
        } else {
            backgroundImage = R.drawable.ic_play_arrow_black_24dp
            scroll_view.scrollBy(0, 0)
            handler.removeCallbacks(scrollDown)
        }

        val imageView = view as ImageView
        imageView.setImageResource(backgroundImage)
    }

    /**
     * Listener that responds to the speed FAB button press
     */
    private val speedIconListener = View.OnClickListener { view: View? ->
        val backgroundImage: Int
        when (speedMode) {
            Speed.NORMAL -> {
                backgroundImage = R.drawable.ic_forward_10_black_24dp
                speedMode = Speed.FAST
                currentSpeed = 200

            }
            Speed.FAST -> {
                backgroundImage = R.drawable.ic_forward_30_black_24dp
                speedMode = Speed.FLASH
                currentSpeed = 100
            }
            Speed.FLASH -> {
                backgroundImage = R.drawable.ic_forward_5_black_24dp
                speedMode = Speed.NORMAL
                currentSpeed = 300
            }
        }
        val imageView = view as ImageView
        imageView.setImageResource(backgroundImage)
    }

    /**
     * Runnable that scrolls the scroll view after the user selected delay
     */
    private val scrollDown = object : Runnable {
        override fun run() {
            scroll_view.scrollBy(0, 10)
            handler.postDelayed(this, currentSpeed)
        }
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

    /**
     * Make screen immersive
     */
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

    /**
     * Shows the system bars by removing all the flags except for the ones
     * that make the content appear under the system bars.
     */
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /**
     * Enum to define the user selected speed
     */
    enum class Speed {
        NORMAL, FAST, FLASH
    }
}
