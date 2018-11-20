package com.example.gooner10.nepaliechord.mainsong

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.allsong.AllSongFragment
import com.example.gooner10.nepaliechord.detailsong.SongDetailActivity
import com.example.gooner10.nepaliechord.favoritesong.FavoriteSongFragment
import com.example.gooner10.nepaliechord.login.LoginActivity
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.recentsong.RecentSongFragment
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainSongActivity : AppCompatActivity(), MainSongContract.MainSongView
        , AllSongFragment.OnAllSongFragmentItemListener
        , FavoriteSongFragment.OnFavoriteFragmentItemListener
        , RecentSongFragment.OnRecentSongFragmentItemListener
        , AnkoLogger {

    private var pagerAdapter: SmartFragmentStatePagerAdapter = MainActivityViewPagerAdapter(supportFragmentManager, this)
    private var presenter: MainSongActivityPresenter = MainSongActivityPresenter(this)
    private lateinit var colorAnimation: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = pagerAdapter

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setColorAnimation()
        setSupportActionBar(toolbar)
        setNavigation()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {

            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
                Log.d(TAG, "Selected onPageSelected position: $position")

            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d(TAG, "Selected onPageScrolled position: $position")
                colorAnimation.currentPlayTime = (((positionOffset + position) * ANIMATION_DURATION).toLong())
                presenter.fetchSong()
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                Log.d(TAG, "Selected onPageScrollStateChanged position: $state")
            }
        })
    }

    private fun setNavigation() {
        val actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.nav_account -> {
                    startActivity(LoginActivity.createIntent(this))
                }
                R.id.nav_logout -> {
                    AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener(object : OnCompleteListener<Void> {
                                override fun onComplete(p0: Task<Void>) {
                                    info("Sign out complete")
                                }
                            })
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
    }

    private fun setColorAnimation() {
        colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.CYAN, Color.GREEN, Color.MAGENTA)
        colorAnimation.duration = (3 - 1) * ANIMATION_DURATION
        colorAnimation.addUpdateListener { animator ->
            pagerAdapter.getRegisteredFragment(viewPager.currentItem).view?.setBackgroundColor(animator.animatedValue as Int)
        }
    }

    override fun onListFragmentInteraction(song: Song) {
        Log.d(TAG, "Song clicked  $song.songTitle")
        intent = Intent(this, SongDetailActivity::class.java)
        intent.putExtra("Song", song)
        startActivity(intent)
    }

    @DebugLog
    override fun displaySong(songList: List<Song>) {
        Log.d(TAG, "songlist: $songList")
        Log.d(TAG, "currentItem: " + viewPager.currentItem)
        Log.d(TAG, "current fragment " + pagerAdapter.getRegisteredFragment(viewPager.currentItem))
        val fragment = pagerAdapter.getRegisteredFragment(viewPager.currentItem)
        fragment.setData(songList)
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

    // Used to define the constant
    companion object {

        private val TAG = MainSongActivity::class.java.simpleName
        private const val ANIMATION_DURATION = 10000000000L

    }
}
