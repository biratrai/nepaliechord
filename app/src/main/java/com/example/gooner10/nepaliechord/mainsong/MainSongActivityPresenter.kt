package com.example.gooner10.nepaliechord.mainsong

import android.content.Context
import com.example.gooner10.nepaliechord.data.SongDataRepositoryImpl
import com.example.gooner10.nepaliechord.mainsong.MainSongContract.MainSongPresenter
import com.example.gooner10.nepaliechord.model.Song

/**
 * Presenter for [MainSongActivity]
 */

class MainSongActivityPresenter(private val songView: MainSongContract.MainSongView) : MainSongPresenter {
    override fun onDataFetched(songList: MutableList<Song>) {
        songView.displaySong(songList)
    }

    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songView as Context)
    
    override fun fetchSong() {
        songDataRepository.getAllSong(this)
    }


}
