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
import com.nepali.echord.R
import com.nepali.echord.BaseFragment
import com.nepali.echord.model.Song

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
class RecentSongFragment : BaseFragment() {
    private var listener: OnRecentSongFragmentItemListener? = null
    private val songList = arrayListOf<Song>()
    private var adapterAll: RecentSongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(SONG_TITLE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_song_fragment, container, false)

        // Set the adapterAll
        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = RecentSongAdapter(songList, listener)
            val dividerItemDecoration = DividerItemDecoration(getContext(), (view.layoutManager as LinearLayoutManager).orientation)
            view.addItemDecoration(dividerItemDecoration)
            adapterAll = view.adapter as RecentSongAdapter
        }
        return view
    }

    override fun setSongData(songList: List<Song>) {
        Log.d(TAG, "song data received")
        adapterAll?.setData(songList)
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
     * fragment to allow an interaction in this fragment to be communicated
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
