package com.nepali.echord.mainsong

import android.content.Context
import com.nepali.echord.data.SongDataRepositoryImpl
import com.nepali.echord.mainsong.MainSongContract.MainSongPresenter
import com.nepali.echord.model.SingerDetail

/**
 * Presenter for [MainSongActivity]
 */

class MainSongActivityPresenter(private val songView: MainSongContract.MainSongView) : MainSongPresenter {
    override fun onDataFetched(songList: MutableList<SingerDetail>) {
        songView.displaySong(songList)
    }

    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songView as Context)

    override fun fetchSong() {
        songDataRepository.getAllArtist(this)
    }


}
