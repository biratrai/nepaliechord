package com.example.gooner10.nepaliechord.favoritesong

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gooner10.nepaliechord.BaseFragment
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class FavoriteSongFragment : BaseFragment() {
    private var listener: OnFavoriteFragmentItemListener? = null
    private var adapter: FavoriteSongAdapter? = null
    private val songList = arrayListOf<Song>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.favorite_song_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = FavoriteSongAdapter(songList, listener)
            val dividerItemDecoration = DividerItemDecoration(getContext(), (view.layoutManager as LinearLayoutManager).orientation)
            view.addItemDecoration(dividerItemDecoration)
            adapter = view.adapter as FavoriteSongAdapter
        }
        return view
    }

    override fun setData(songList: List<Song>) {
        Log.d(TAG, "song data received")
        adapter?.setData(songList)
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
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFavoriteFragmentItemListener {
        fun onListFragmentInteraction(song: Song)
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
