package com.nepali.echord.detailsong

import com.nepali.echord.data.SongDataRepository
import com.nepali.echord.model.SongDetail
import javax.inject.Inject

class SongDetailPresenter @Inject constructor(private val songDataRepository: SongDataRepository)
    : SongDetailContract.SongDetailPresenter {

    private lateinit var songDetailView: SongDetailContract.SongDetailView

    override fun fetchSongDetail(singerId: String) {
        songDataRepository.getSongDetailById(singerId, this)
    }

    override fun onSongDataFetched(songDetail: SongDetail) {
        songDetailView.displaySongDetail(songDetail)
    }

    override fun setView(songDetailView: SongDetailContract.SongDetailView) {
        this.songDetailView = songDetailView
    }
}