package com.example.gooner10.nepaliechord.detailsong

import android.content.Context
import com.example.gooner10.nepaliechord.data.SongDataRepositoryImpl
import com.example.gooner10.nepaliechord.model.SongDetail

class SongDetailPresenter(private val songDetailView: SongDetailContract.SongDetailView) : SongDetailContract.SongDetailPresenter {
    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songDetailView as Context)

    override fun fetchSongDetail(singerId: String) {
        songDataRepository.getSongDetailById(singerId, this)
    }

    override fun onSongDataFetched(songDetail: SongDetail) {
        songDetailView.displaySongDetail(songDetail)
    }
}