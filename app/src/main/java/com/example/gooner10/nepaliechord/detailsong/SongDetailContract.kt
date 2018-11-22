package com.example.gooner10.nepaliechord.detailsong

import com.example.gooner10.nepaliechord.model.SongDetail

/**
 * Contract for the mainActivity
 */

interface SongDetailContract {
    interface SongDetailView {
        fun displaySongDetail(songDetail: SongDetail)
    }

    interface SongDetailPresenter {
        fun fetchSongDetail(singerId: String)

        fun onSongDataFetched(songDetail: SongDetail)
    }
}
