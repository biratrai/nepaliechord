package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.artistsong.ArtistSongActivityPresenter
import com.example.gooner10.nepaliechord.detailsong.SongDetailContract
import com.example.gooner10.nepaliechord.mainsong.MainSongActivityPresenter
import com.example.gooner10.nepaliechord.model.Song

interface SongDataRepository {
    fun getAllArtist(mainSongActivityPresenter: MainSongActivityPresenter)
    fun getFavoriteSong(): List<Song>
    fun getRecentSong(): List<Song>
    fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter)
    fun getSongDetailById(songId: String, view: SongDetailContract.SongDetailPresenter)
}