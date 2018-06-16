package com.example.gooner10.nepaliechord.mainsong

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.example.gooner10.nepaliechord.allsong.AllSongFragment
import com.example.gooner10.nepaliechord.favoritesong.FavoriteSongFragment

/**
 * Pager Adapter for ViewPager in [MainActivity]
 */

class MainActivityViewPagerAdapter(fm: FragmentManager) : SmartFragmentStatePagerAdapter(fm) {
    private val NUM_ITEMS = 2
    private val TAG = MainActivityViewPagerAdapter::class.java.simpleName
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return AllSongFragment.newInstance("Songs")
            1 -> return FavoriteSongFragment.newInstance()
            else ->
                Log.e(TAG, "Not defined")
        }
        return null
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "Songs" else "Favorite"
    }
}
