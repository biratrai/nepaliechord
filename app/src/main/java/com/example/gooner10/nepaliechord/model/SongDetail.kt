package com.example.gooner10.nepaliechord.model

/**
 * Song detail pojo
 */
class SongDetail() {
    var songId: String? = null
    var songLyrics: String? = null

    constructor(songId: String,
                songLyrics: String) : this()
}