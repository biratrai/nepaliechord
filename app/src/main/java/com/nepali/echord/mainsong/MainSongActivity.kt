package com.nepali.echord.mainsong

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.MenuItem
import com.nepali.echord.NepaliChordConstant
import com.nepali.echord.R
import com.nepali.echord.R.id.*
import com.nepali.echord.allsong.AllSongFragment
import com.nepali.echord.artistsong.ArtistSongActivity
import com.nepali.echord.detailsong.SongDetailActivity
import com.nepali.echord.favoritesong.FavoriteSongFragment
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import com.nepali.echord.recentsong.RecentSongFragment
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_song_row.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainSongActivity : AppCompatActivity()
        , MainSongContract.MainSongView
        , AllSongFragment.OnAllSongFragmentItemListener
        , FavoriteSongFragment.OnFavoriteFragmentItemListener
        , RecentSongFragment.OnRecentSongFragmentItemListener
        , AnkoLogger {

    private var pagerAdapter: SmartFragmentStatePagerAdapter = MainActivityViewPagerAdapter(supportFragmentManager, this)
    private var presenter: MainSongActivityPresenter = MainSongActivityPresenter(this)
    private lateinit var colorAnimation: ValueAnimator
    private var isFirst = true

    //region Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        viewPager.adapter = pagerAdapter

        bottomNavigationBar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

//        setColorAnimation()
        setSupportActionBar(app_toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setNavigation()

        viewPager.addOnPageChangeListener(onPageChangeListener())

    }

    private fun onPageChangeListener(): OnPageChangeListener {
        return object : OnPageChangeListener {

            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
                info("Selected onPageSelected position: $position")
                setBottomNavigationBarSelectedItem(position)
                presenter.fetchSong(position)
            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                info("Selected onPageScrolled position: $position")
                //                colorAnimation.currentPlayTime = (((positionOffset + position) * ANIMATION_DURATION).toLong())
                if (isFirst) {
                    presenter.fetchSong(position)
                    isFirst = false
                }
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                info("Selected onPageScrollStateChanged position: $state")
            }
        }
    }

    private fun setBottomNavigationBarSelectedItem(position: Int) {
        when (position) {
            0 -> bottomNavigationBar.selectedItemId = navigation_home
            1 -> bottomNavigationBar.selectedItemId = navigation_favorite
            2 -> bottomNavigationBar.selectedItemId = navigation_new_releases
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion

    private fun setColorAnimation() {
        colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.CYAN, Color.GREEN, Color.MAGENTA)
        colorAnimation.duration = (3 - 1) * ANIMATION_DURATION
        colorAnimation.addUpdateListener { animator ->
            pagerAdapter.getRegisteredFragment(viewPager.currentItem).view?.setBackgroundColor(animator.animatedValue as Int)
        }
    }

    //region Fragment Listener
    override fun onFavoriteFragmentInteraction(song: Song) {
        info("onFavoriteFragmentInteraction: $song")
        startSongDetailActivity(song)
    }

    override fun onRecentFragmentInteraction(song: Song) {
        info("onRecentFragmentInteraction: $song")
        startSongDetailActivity(song)
    }

    private fun startSongDetailActivity(song: Song) {
        Log.d(TAG, "Song clicked  $song.songTitle")
        intent = Intent(this, SongDetailActivity::class.java)
        intent.putExtra(NepaliChordConstant.SONG_DETAIL_INTENT, song)
        startActivity(intent)
    }

    override fun onAllSongFragmentInteraction(singer: SingerDetail) {
        Log.d(TAG, "Song clicked  ${singer.singerName}")
        intent = Intent(this, ArtistSongActivity::class.java)
        intent.putExtra("Singer", singer)

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this, singerIcon, "artist-transition")
            // Apply activity transition
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            startActivity(intent, options.toBundle())
        } else {
            // Swap without transition
            startActivity(intent)
        }
    }
    // endregion

    //region MainSongContract methods
    @DebugLog
    override fun displayArtistSong(singerList: MutableList<SingerDetail>) {
        Log.d(TAG, "displayArtistSong songlist: $singerList")
        val currentFragment = pagerAdapter.getRegisteredFragment(viewPager.currentItem)
        currentFragment.setSingerData(singerList)
    }

    override fun displayRecentSong(songList: MutableList<Song>) {
        Log.d(TAG, "displayRecentSong songlist: $songList")
        val currentFragment = pagerAdapter.getRegisteredFragment(viewPager.currentItem)
        currentFragment.setSongData(songList)
    }

    override fun displayFavoriteSong(songList: MutableList<Song>) {
        Log.d(TAG, "displayFavoriteSong songlist: $songList")
        val currentFragment = pagerAdapter.getRegisteredFragment(viewPager.currentItem)
        currentFragment.setSongData(songList)
    }
    //endregion

    //region Navigation Listener
    private fun setNavigation() {
        val actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                Log.i(TAG, "home")
                viewPager.setCurrentItem(0, true)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                Log.i(TAG, "favorite")
                viewPager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new_releases -> {
                viewPager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    //endregion

    // Used to define the constant
    companion object {

        private val TAG = MainSongActivity::class.java.simpleName
        private const val ANIMATION_DURATION = 10000000000L

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
