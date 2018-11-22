package com.nepali.echord.detailsong

import android.content.Context
import com.nepali.echord.data.SongDataRepositoryImpl
import com.nepali.echord.model.SongDetail

class SongDetailPresenter(private val songDetailView: SongDetailContract.SongDetailView) : SongDetailContract.SongDetailPresenter {
    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songDetailView as Context)

    override fun fetchSongDetail(singerId: String) {
        songDataRepository.getSongDetailById(singerId, this)
    }

    override fun onSongDataFetched(songDetail: SongDetail) {
        songDetailView.displaySongDetail(songDetail)
    }
}