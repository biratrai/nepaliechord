package com.example.gooner10.nepaliechord.mainsong

import com.example.gooner10.nepaliechord.model.SingerDetail

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
