package com.nepali.echord

import android.net.Uri
import android.support.v4.app.Fragment
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BaseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
abstract class BaseFragment : Fragment() {

    open fun setSongData(songList: List<Song>) {

    }

    open fun setSingerData(singerList: List<SingerDetail>) {}

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
