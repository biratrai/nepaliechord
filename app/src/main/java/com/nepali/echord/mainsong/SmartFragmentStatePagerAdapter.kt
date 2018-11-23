package com.nepali.echord.mainsong

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import com.nepali.echord.BaseFragment

import hugo.weaving.DebugLog

/*
   Extension of FragmentStatePagerAdapter which intelligently caches
   all active fragments and manages the currentFragment lifecycles.
   Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
*/
abstract class SmartFragmentStatePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<BaseFragment>()

    // Register the currentFragment when the item is instantiated
    @DebugLog
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as BaseFragment
        registeredFragments.put(position, fragment)
        Log.d("TAG", "currentFragment: $fragment")
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the currentFragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): BaseFragment {
        return registeredFragments.get(position)
    }
}
