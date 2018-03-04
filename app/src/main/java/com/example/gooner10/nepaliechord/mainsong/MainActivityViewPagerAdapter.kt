package com.example.gooner10.nepaliechord.mainsong

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

/**
 * Pager Adapter for ViewPager in [MainActivity]
 */

class MainActivityViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val NUM_ITEMS = 2
    private val TAG = MainActivityViewPagerAdapter::class.java.simpleName
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return SongFragment.newInstance()
            1 -> return FavoriteMusicFragment.newInstance()
            else ->
                Log.e(TAG, "Not defined")
        }
        return null
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Page $position"
    }
}
