package com.example.collectorapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

open class MainActivity : AppCompatActivity() {


    private var titlesList = mutableListOf<String>()
    private var descriptionsList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    private lateinit var listView : ListView

    var listOfGames : ArrayList<String> = ArrayList()
    private var game : CollectionInfo = CollectionInfo("","","")


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreference : SharedPreferences = getSharedPreferences("COLLECTOR_APP", Context.MODE_PRIVATE)
        val editor= sharedPreference.edit()
        title = getString(R.string.app_name)
        val btnShowCollection : Button = findViewById(R.id._btnShowCollection)
        val btnAddToList : Button = findViewById(R.id._btnAddToList)
        val gameTitle : EditText = findViewById(R.id._etTitle)
        val gameDesc : EditText = findViewById(R.id._etDescription)
        val cardGame : SwitchCompat = findViewById(R.id._swCardGame)
        val diceGame : SwitchCompat = findViewById(R.id._swDiceGame)
        val RPG : SwitchCompat = findViewById(R.id._swRPG)
        val boardGame : SwitchCompat = findViewById(R.id._swBoardGame)

        //NEED TO FIX THIS ISSUE, SEE BELOW
//        setImages(cardGame.isChecked, diceGame.isChecked,RPG.isChecked, boardGame.isChecked)


        btnShowCollection.setOnClickListener{
            val collectionIntent = Intent(this, CollectionListAct::class.java)
            collectionIntent.putExtra("gameInfo", game.Title)
            startActivity(collectionIntent)
        }

        //ADDS GAME TO LIST
        btnAddToList.setOnClickListener{
            game.Title = gameTitle.text.toString()
            game.Desc = gameDesc.text.toString()

            Log.d("SwitchTest", cardGame.isChecked.toString())

            if(gameTitle.text.toString() == " "){
                Toast.makeText(applicationContext, getString(R.string.toast_No_Title), Toast.LENGTH_LONG).show()
            }
            else{
                getCategory(cardGame.isChecked, diceGame.isChecked,RPG.isChecked, boardGame.isChecked)
                listOfGames.add("TITLE : ${game.Title}\nDESCRIPTION: ${game.Desc}\nCATEGORY: ${game.Category}")


            }
//            editor.putString(game.Title, game.toString())
//            editor.apply()
            editor.clear()

            for(i in listOfGames){
                Log.d("itemArrayList", i)

            }
        }


    }

    //NOT SHOWING IMAGE WHEN SWITCH IS TRUE.
    private fun setImages(cardSwitch : Boolean, diceSwitch : Boolean, rpgSwitch : Boolean, boardSwitch : Boolean){
        val imgOfGame : ImageView = findViewById(R.id._imgView)
        when(cardSwitch){
            true -> {imgOfGame.setImageResource(R.drawable.card_game)
                imgOfGame.visibility = View.VISIBLE}
        }
        when(diceSwitch){
            true -> {imgOfGame.setImageResource(R.drawable.dice_game)
                imgOfGame.visibility = View.VISIBLE}
        }
        when(rpgSwitch){
            true ->{imgOfGame.setImageResource(R.drawable.rpg)
                imgOfGame.visibility = View.VISIBLE}
        }
        when(boardSwitch){
            true -> {imgOfGame.setImageResource(R.drawable.board_game)
                imgOfGame.visibility = View.VISIBLE}
        }

    }

    private fun getCategory(cardSwitch : Boolean, diceSwitch : Boolean, rpgSwitch : Boolean, boardSwitch : Boolean){
        val strCardGame = getString(R.string.str_card_game)
        val strDiceGame = getString(R.string.str_dice_game)
        val strRPG = getString(R.string.str_RPG)
        val strboardGame = getString(R.string.str_board_game)
        game.Category = ""
        when(cardSwitch){
            true -> game.Category += "$strCardGame "

        }
        when(diceSwitch){
            true -> game.Category += "$strDiceGame "
        }
        when(rpgSwitch){
            true -> game.Category += "$strRPG "
        }
        when(boardSwitch){
            true -> game.Category += "$strboardGame "
        }
    }

    private fun resetArray(){
        if(listOfGames.size > 0 ){
            listOfGames.clear()
        }
    }


}