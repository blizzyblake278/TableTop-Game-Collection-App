package com.example.collectorapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CollectionInfo(var Title: String, var Desc: String, var Category: String, var PlayerCount : String) : Parcelable {

    override fun toString(): String {
        return "TITLE: ${Title}\nDESCRIPTION: $Desc\nCATEGORY: $Category\nNUMBER OF PLAYERS: $PlayerCount"
    }

}