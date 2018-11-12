package com.example.gooner10.nepaliechord.mainsong

import com.example.gooner10.nepaliechord.data.SongDataRepositoryImpl
import com.example.gooner10.nepaliechord.mainsong.MainSongContract.MainSongPresenter

/**
 * Presenter for [MainSongActivity]
 */

class MainSongActivityPresenter(private val songView: MainSongContract.MainSongView) : MainSongPresenter {

    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl()
    
    override fun fetchSong() {

        val songList = songDataRepository.getAllSong()
        songView.displaySong(songList)
    }
}
