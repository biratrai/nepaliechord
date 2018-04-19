package com.example.gooner10.nepaliechord.mainsong

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.detailsong.SongDetailActivity
import com.example.gooner10.nepaliechord.model.Song
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainSongContract.MainSongView, AllSongFragment.OnAllSongFragmentItemListener, FavoriteSongFragment.OnFavoriteFragmentItemListener {
    private val TAG = MainActivity::class.java.simpleName
    private var pagerAdapter: SmartFragmentStatePagerAdapter = MainActivityViewPagerAdapter(supportFragmentManager)
    private var presenter: MainSongActivityPresenter = MainSongActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = pagerAdapter
//        slidingTabs.setupWithViewPager(viewPager)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {

            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
                Log.d(TAG, "Selected onPageSelected position: $position")

            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d(TAG, "Selected onPageScrolled position: $position")
                presenter.fetchSong()
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                Log.d(TAG, "Selected onPageScrollStateChanged position: $state")
            }
        })
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

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
