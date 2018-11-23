package com.nepali.echord.recentsong

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
import com.nepali.echord.allsong.AllSongAdapter
import com.nepali.echord.model.Song
import kotlinx.android.synthetic.main.favorite_song_fragment.*
import kotlinx.android.synthetic.main.recent_song_fragment.*

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
class RecentSongFragment : BaseFragment() {
    private var listener: OnRecentSongFragmentItemListener? = null
    private val songList = arrayListOf<Song>()
    private var adapterAll: RecentSongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(SONG_TITLE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recent_song_fragment, container, false)
        val recentSongRecyclerView = view.findViewById(R.id.recentSongList) as RecyclerView
        // Set the adapterAll
        recentSongRecyclerView.layoutManager = LinearLayoutManager(context)
        recentSongRecyclerView.adapter = RecentSongAdapter(songList, listener)
        val dividerItemDecoration = DividerItemDecoration(context, (recentSongRecyclerView.layoutManager as LinearLayoutManager).orientation)
        recentSongRecyclerView.addItemDecoration(dividerItemDecoration)
        return view
    }

    override fun setSongData(songList: List<Song>) {
        Log.d(TAG, "song data received")
        if (songList.isNotEmpty()) {
            recentSongList.visibility = View.VISIBLE
            emptyRecentSongData.visibility = View.GONE
            adapterAll?.setData(songList)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnRecentSongFragmentItemListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnAllSongFragmentItemListener")
        }
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
    interface OnRecentSongFragmentItemListener {
        fun onRecentFragmentInteraction(song: Song)
    }

    companion object {
        private const val SONG_TITLE = "song-title"
        private val TAG = RecentSongFragment::class.java.simpleName

        fun newInstance(): RecentSongFragment {
            val fragment = RecentSongFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
