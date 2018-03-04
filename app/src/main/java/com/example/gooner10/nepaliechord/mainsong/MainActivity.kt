package com.example.gooner10.nepaliechord.mainsong

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainSongContract.MainSongView {
    private val TAG = MainActivity::class.java.simpleName
    private var pagerAdapter: SmartFragmentStatePagerAdapter? = null
    private var presenter: MainSongActivityPresenter = MainSongActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pagerAdapter = MainActivityViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        presenter.fetchSong()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun displaySong(songList: MutableList<Song>) {
        Log.d(TAG, "songlist: " + songList)
        Log.d(TAG, "currentItem: " + viewPager.currentItem)
        Log.d(TAG, "current fragment " + pagerAdapter?.getRegisteredFragment(viewPager.currentItem));
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
