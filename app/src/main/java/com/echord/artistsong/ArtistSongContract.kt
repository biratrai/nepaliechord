package com.nepali.echord.artistsong

import com.nepali.echord.model.Song

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
