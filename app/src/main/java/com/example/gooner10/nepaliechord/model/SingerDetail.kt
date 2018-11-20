package com.example.gooner10.nepaliechord.model

class SingerDetail() {
    var singerId: String? = null
    var singerName: String? = null
    var singerPhoto: String? = null

    constructor(singerId: String
                , singerName: String
                , singerPhoto: String) : this() {
        this.singerId = singerId
        this.singerName = singerName
        this.singerPhoto = singerPhoto
    }
}