package com.example.collectorapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class CollectionListAct : AppCompatActivity() {
    val gameList : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_list)
        val returnButton : Button = findViewById(R.id._btnGoBack)
        val bundle : Bundle? = intent.extras
        val strGameInfo : String? = intent.getStringExtra("gameInfo")
        val sharedPreference : SharedPreferences = getSharedPreferences("COLLECTOR_APP", Context.MODE_PRIVATE)
        val gameInfo = sharedPreference.getString("gameInfo", null)

        val myMap : Map<String, String> = sharedPreference.all as Map<String, String>
        for((key, value) in myMap){
            gameList.add(value)
            gameList.sortBy{(value)}
            Log.d("MAP_TEST", value)
        }

        val listView : ListView = findViewById(R.id.listView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.collection_layout, gameList)
        listView.adapter = adapter


        returnButton.setOnClickListener{
            val mainActivity = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(mainActivity)
        }

    }


}
