package com.nepali.echord.detailsong

import com.nepali.echord.model.SongDetail

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
