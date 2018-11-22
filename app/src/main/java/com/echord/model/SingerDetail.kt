package com.nepali.echord.model

import android.os.Parcel
import android.os.Parcelable

class SingerDetail() : Parcelable {
    var singerId: String? = null
    var singerName: String? = null
    var singerPhoto: String? = null

    constructor(parcel: Parcel) : this() {
        singerId = parcel.readString()
        singerName = parcel.readString()
        singerPhoto = parcel.readString()
    }

    constructor(singerId: String
                , singerName: String
                , singerPhoto: String) : this() {
        this.singerId = singerId
        this.singerName = singerName
        this.singerPhoto = singerPhoto
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(singerId)
        parcel.writeString(singerName)
        parcel.writeString(singerPhoto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SingerDetail> {
        override fun createFromParcel(parcel: Parcel): SingerDetail {
            return SingerDetail(parcel)
        }

        override fun newArray(size: Int): Array<SingerDetail?> {
            return arrayOfNulls(size)
        }
    }
}