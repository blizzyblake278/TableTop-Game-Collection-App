package com.example.collectorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CollectionListAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_layout)
        val bundle : Bundle? = intent.extras
        val gameInfo : String? = intent.getStringExtra("gameInfo")

        val tv : TextView = findViewById(R.id._tv2)
        tv.text = gameInfo

    }
}