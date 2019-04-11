package com.nepali.echord.mainsong

import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song

/**
 * Contract for the mainActivity
 */

interface MainSongContract {
    interface MainSongView {
        fun displayArtistSong(singerList: MutableList<SingerDetail>)
        fun displayRecentSong(songList: MutableList<Song>)
        fun displayFavoriteSong(songList: MutableList<Song>)
    }

    interface MainSongPresenter {
        fun setView(mainSongView: MainSongView)
        fun fetchSong(position: Int)

        fun onArtistDataFetched(singerList: MutableList<SingerDetail>)
        fun onRecentDataFetched(songList: MutableList<Song>)
        fun onFavoriteDataFetched(songList: MutableList<Song>)
    }
}
