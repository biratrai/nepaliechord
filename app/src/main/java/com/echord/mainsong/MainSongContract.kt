package com.nepali.echord.mainsong

import com.nepali.echord.model.SingerDetail

/**
 * Contract for the mainActivity
 */

interface MainSongContract {
    interface MainSongView {
        fun displaySong(singerList: MutableList<SingerDetail>)
    }

    interface MainSongPresenter {
        fun fetchSong(position: Int)

        fun onArtistDataFetched(singerList: MutableList<SingerDetail>)
        fun onRecentDataFetched(singerList: MutableList<SingerDetail>)
        fun onFavoriteDataFetched(singerList: MutableList<SingerDetail>)
    }
}
