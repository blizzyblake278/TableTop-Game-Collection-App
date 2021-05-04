package com.example.collectorapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList

class CollectionListAct : AppCompatActivity() {
    val gameList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_list)
        val returnButton: Button = findViewById(R.id._btnGoBack)
        val deleteButton: Button = findViewById(R.id._btnDelete)
        val bundle: Bundle? = intent.extras
        val strGameInfo: String? = intent.getStringExtra("gameInfo")
        val sharedPreference: SharedPreferences = getSharedPreferences("COLLECTOR_APP", Context.MODE_PRIVATE)
        val editor= sharedPreference.edit()


        val myMap: Map<String, String> = sharedPreference.all as Map<String, String>
        for ((key, value) in myMap) {
            gameList.add(value)
            Log.d("MAP_TEST", value)

        }

        sortValues(myMap)

        val listView: ListView = findViewById(R.id.listView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.collection_layout, gameList)
        listView.adapter = adapter


        returnButton.setOnClickListener {
            val mainActivity = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(mainActivity)
        }

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                val keyArray = myMap.keys.toTypedArray()

                deleteButton.visibility = VISIBLE
                deleteButton.setOnClickListener {

                    val atIndex = keyArray.get(position)
                    Log.d("keyposition", keyArray.get(position))


                    gameList.removeAt(position)
                    adapter.notifyDataSetChanged()
                    listView.adapter

                    Log.d("keysID",id.toString())
                    Log.d("keysEntries", myMap.entries.toString())

                    editor.remove(atIndex)
                    editor.apply()
                    Log.d("gamelist", gameList.toString())

                }

            }
        }
        adapter.notifyDataSetChanged()
    }

    ///////PRIVATE FUNCTIONS///////

    private fun sortValues(myMap : Map<String, String>){
        gameList.clear()
        Log.d("sortedMap", myMap.toSortedMap().toString())
        for(value in myMap.values.toSortedSet()){

            gameList.add(value)
        }
    }


}





