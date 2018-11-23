package com.nepali.echord.data

import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter

interface SongDataRepository {
    fun fetchAllArtistData(mainSongActivityPresenter: MainSongActivityPresenter)
    fun fetchFavoriteSong(mainSongActivityPresenter: MainSongActivityPresenter)
    fun fetchRecentSong(mainSongActivityPresenter: MainSongActivityPresenter)
    fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter)
    fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter)
}