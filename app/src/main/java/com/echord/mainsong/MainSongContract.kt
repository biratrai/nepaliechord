package com.nepali.echord.mainsong

import com.nepali.echord.model.SingerDetail

/**
 * Contract for the mainActivity
 */

interface MainSongContract {
    interface MainSongView {
        fun displaySong(songList: MutableList<SingerDetail>)
    }

    interface MainSongPresenter {
        fun fetchSong()

        fun onDataFetched(songList: MutableList<SingerDetail>)
    }
}
