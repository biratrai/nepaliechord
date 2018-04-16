package com.example.gooner10.nepaliechord.mainsong

import com.example.gooner10.nepaliechord.model.Song

/**
 * Contract for the mainActivity
 */

interface MainSongContract {
    interface MainSongView {
        fun displaySong(songList: List<Song>)
    }

    interface MainSongPresenter {
        fun fetchSong()
    }
}
