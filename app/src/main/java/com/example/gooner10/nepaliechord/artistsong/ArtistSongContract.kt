package com.example.gooner10.nepaliechord.artistsong

import com.example.gooner10.nepaliechord.model.SingerDetail
import com.example.gooner10.nepaliechord.model.Song

/**
 * Contract for the mainActivity
 */

interface ArtistSongContract {
    interface ArtistSongView {
        fun displayArtistSong(songList: MutableList<Song>)
    }

    interface ArtistSongPresenter {
        fun fetchArtistSong(singerId: String)

        fun onArtistDataFetched(songList: MutableList<Song>)
    }
}
