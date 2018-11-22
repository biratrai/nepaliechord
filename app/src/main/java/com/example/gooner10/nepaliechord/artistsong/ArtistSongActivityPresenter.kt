package com.example.gooner10.nepaliechord.artistsong

import android.content.Context
import com.example.gooner10.nepaliechord.artistsong.ArtistSongContract.ArtistSongPresenter
import com.example.gooner10.nepaliechord.artistsong.ArtistSongContract.ArtistSongView
import com.example.gooner10.nepaliechord.data.SongDataRepositoryImpl
import com.example.gooner10.nepaliechord.model.Song

/**
 * Presenter for [ArtistSongActivity]
 */

class ArtistSongActivityPresenter(private val songView: ArtistSongView) : ArtistSongPresenter {
    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songView as Context)

    override fun onArtistDataFetched(songList: MutableList<Song>) {
        songView.displayArtistSong(songList)
    }

    override fun fetchArtistSong(singerId: String) {
        songDataRepository.getSongByArtist(singerId, this)
    }

}
