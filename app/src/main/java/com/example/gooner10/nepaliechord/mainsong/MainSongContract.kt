package com.example.gooner10.nepaliechord.mainsong

import com.example.gooner10.nepaliechord.model.Song

/**
 * Contract for the mainActivity
 */

interface MainSongContract {
    interface MainSongView {
        fun displaySong(songList: MutableList<Song>)
    }

    interface MainSongPresenter {
        fun fetchSong()

        fun onDataFetched(songList: MutableList<Song>)
    }
}
