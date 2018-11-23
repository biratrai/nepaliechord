package com.nepali.echord.mainsong

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment
import com.nepali.echord.favoritesong.FavoriteSongFragment
import com.nepali.echord.recentsong.RecentSongFragment

/**
 * Pager Adapter for ViewPager in [MainSongActivity]
 */

class MainActivityViewPagerAdapter(fm: FragmentManager, private val context: Context) : SmartFragmentStatePagerAdapter(fm) {

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
            0 -> context.getString(R.string.title_home)
            1 -> context.getString(R.string.title_favorite_songs)
            else -> context.getString(R.string.title_recent_song)
        }
    }

    // Used to define the constant
    companion object {
        private const val NUM_ITEMS = 3
        private val TAG = MainActivityViewPagerAdapter::class.java.simpleName
    }
}
