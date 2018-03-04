package com.example.gooner10.nepaliechord.mainsong

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.DummyContent
import com.example.gooner10.nepaliechord.model.DummyContent.DummyItem
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
class SongFragment : BaseFragment() {
    private val TAG = SongFragment::class.java.simpleName
    private val SONG_TITLE = "song-title"
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.getString(SONG_TITLE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_song_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = SongFragmentAdapter(DummyContent.ITEMS, listener)
        }
        return view
    }

    override fun setData(songList: List<Song>) {
        Log.d(TAG, "song data received")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
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
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: DummyItem)
    }

    companion object {
        private const val SONG_TITLE = "song-title"

        fun newInstance(title: String): SongFragment {
            val fragment = SongFragment()
            val args = Bundle()
            args.putString(SONG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }
}
