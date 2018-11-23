package com.nepali.echord.allsong

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
import com.nepali.echord.model.SingerDetail

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
class AllSongFragment : BaseFragment() {
    private var listener: OnAllSongFragmentItemListener? = null
    private val songList = arrayListOf<SingerDetail>()
    private var adapterAll: AllSongAdapter? = null

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
            view.adapter = AllSongAdapter(songList, listener)
            val dividerItemDecoration = DividerItemDecoration(getContext(), (view.layoutManager as LinearLayoutManager).orientation)
            view.addItemDecoration(dividerItemDecoration)
            adapterAll = view.adapter as AllSongAdapter
        }
        return view
    }

    override fun setSingerData(singerList: List<SingerDetail>) {
        Log.d(TAG, "song data received")
        adapterAll?.setData(singerList)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnAllSongFragmentItemListener) {
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
    interface OnAllSongFragmentItemListener {
        fun onAllSongFragmentInteraction(singer: SingerDetail)
    }

    companion object {
        private const val SONG_TITLE = "song-title"
        private val TAG = AllSongFragment::class.java.simpleName

        fun newInstance(title: String): AllSongFragment {
            val fragment = AllSongFragment()
            val args = Bundle()
            args.putString(SONG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }
}