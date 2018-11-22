package com.nepali.echord.data

import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter
import com.nepali.echord.model.Song

interface SongDataRepository {
    fun getAllArtist(mainSongActivityPresenter: MainSongActivityPresenter)
    fun getFavoriteSong(): List<Song>
    fun getRecentSong(): List<Song>
    fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter)
    fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter)
}