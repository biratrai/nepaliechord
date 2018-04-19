package com.example.gooner10.nepaliechord.mainsong

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val TAG = FavoriteSongFragment::class.java.simpleName
    private var listener: OnListFragmentInteractionListener? = null
    private var adapter: MyMusicRecyclerViewAdapter? = null
    private val songList = arrayListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.favorite_song_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = MyMusicRecyclerViewAdapter(songList, listener)
            val dividerItemDecoration = DividerItemDecoration(getContext(), (view.layoutManager as LinearLayoutManager).orientation)
            view.addItemDecoration(dividerItemDecoration)
            adapter = view.adapter as MyMusicRecyclerViewAdapter
        }
        return view
    }

    override fun setData(songList: List<Song>) {
        Log.d(TAG, "song data received")
        adapter?.setData(songList)
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
        fun onListFragmentInteraction(item: Song)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(): FavoriteSongFragment {
            val fragment = FavoriteSongFragment()
            val args = Bundle()
//            args.putInt(ARG_COLUMN_COUNT,)
            fragment.arguments = args
            return fragment
        }
    }
}
