package com.nepali.echord.model

/**
 * Song detail pojo
 */
class SongDetail() {
    var songId: String? = null
    var songLyrics: String? = null

    constructor(songId: String,
                songLyrics: String) : this() {
        this.songId = songId
        this.songLyrics = songLyrics
    }
}