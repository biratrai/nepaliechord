package com.nepali.echord.favoritesong

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nepali.echord.BaseFragment
import com.nepali.echord.R
import com.nepali.echord.model.Song
import kotlinx.android.synthetic.main.all_song_fragment.*
import kotlinx.android.synthetic.main.favorite_song_fragment.*

/**
 * A currentFragment representing a list of Items.
 *
 *
 * Activities containing this currentFragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the currentFragment manager to instantiate the
 * currentFragment (e.g. upon screen orientation changes).
 */
class FavoriteSongFragment : BaseFragment() {
    private var listener: OnFavoriteFragmentItemListener? = null
    private var adapter: FavoriteSongAdapter? = null
    private val songList = arrayListOf<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.favorite_song_fragment, container, false)
        val favoriteSongRecyclerView = view.findViewById(R.id.favoriteSongList) as RecyclerView
        favoriteSongRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteSongRecyclerView.adapter = FavoriteSongAdapter(songList, listener)
        val dividerItemDecoration = DividerItemDecoration(context, (favoriteSongRecyclerView.layoutManager as LinearLayoutManager).orientation)
        favoriteSongRecyclerView.addItemDecoration(dividerItemDecoration)
        adapter = favoriteSongRecyclerView.adapter as FavoriteSongAdapter
        return view
    }

    override fun setSongData(songList: List<Song>) {
        Log.d(TAG, "song data received")
        if (songList.isNotEmpty()) {
            favoriteSongList.visibility = View.VISIBLE
            emptyFavoriteList.visibility = View.GONE
            adapter?.setData(songList)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFavoriteFragmentItemListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnAllSongFragmentItemListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * currentFragment to allow an interaction in this currentFragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFavoriteFragmentItemListener {
        fun onFavoriteFragmentInteraction(song: Song)
    }

    companion object {
        private val TAG = FavoriteSongFragment::class.java.simpleName

        fun newInstance(): FavoriteSongFragment {
            val fragment = FavoriteSongFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
