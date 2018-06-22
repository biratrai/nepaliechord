package com.example.gooner10.nepaliechord.mainsong

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.example.gooner10.nepaliechord.allsong.AllSongFragment
import com.example.gooner10.nepaliechord.favoritesong.FavoriteSongFragment
import com.example.gooner10.nepaliechord.recentsong.RecentSongFragment

/**
 * Pager Adapter for ViewPager in [MainActivity]
 */

class MainActivityViewPagerAdapter(fm: FragmentManager) : SmartFragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return AllSongFragment.newInstance("Songs")
            1 -> return FavoriteSongFragment.newInstance()
            2 -> return RecentSongFragment.newInstance()
            else ->
                Log.e(TAG, "Not defined")
        }
        return null
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Songs"
            1 -> "Favorite"
            else -> "Recent"
        }
    }

    // Used to define the constant
    companion object {
        private const val NUM_ITEMS = 3
        private val TAG = MainActivityViewPagerAdapter::class.java.simpleName
    }
}
