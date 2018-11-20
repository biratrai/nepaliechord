package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.mainsong.MainSongActivityPresenter
import com.example.gooner10.nepaliechord.model.Song

interface SongDataRepository {
    fun getAllArtist(mainSongActivityPresenter: MainSongActivityPresenter)
    fun getFavoriteSong(): List<Song>
    fun getRecentSong(): List<Song>
    fun getSongByArtist(singerId: String): List<Song>
}