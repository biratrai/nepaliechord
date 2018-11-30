package com.nepali.echord.artistsong

import android.content.Context
import com.nepali.echord.artistsong.ArtistSongContract.ArtistSongPresenter
import com.nepali.echord.artistsong.ArtistSongContract.ArtistSongView
import com.nepali.echord.data.SongDataRepository
import com.nepali.echord.data.SongDataRepositoryImpl
import com.nepali.echord.model.Song

/**
 * Presenter for [ArtistSongActivity]
 */

class ArtistSongActivityPresenter(private val songView: ArtistSongView) : ArtistSongPresenter {
    private val songDataRepository: SongDataRepository = SongDataRepositoryImpl(songView as Context)

    override fun onArtistDataFetched(songList: MutableList<Song>) {
        songView.displayArtistSong(songList)
    }

    override fun fetchArtistSong(singerId: String) {
        songDataRepository.getSongByArtist(singerId, this)
    }

}
