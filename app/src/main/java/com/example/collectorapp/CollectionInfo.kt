package com.example.collectorapp

class CollectionInfo(var Title: String, var Desc: String, var Category: String, var PlayerCount : String) {

    override fun toString(): String {
        return "TITLE: ${Title}\nDESCRIPTION: $Desc\nCATEGORY: $Category\nNUMBER OF PLAYERS: $PlayerCount"
    }



}