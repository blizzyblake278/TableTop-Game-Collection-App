package com.example.collectorapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import java.util.*
import kotlin.collections.ArrayList

open class MainActivity : AppCompatActivity() {


    var listOfGames : ArrayList<String> = ArrayList()
    private var game : CollectionInfo = CollectionInfo("","","", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreference : SharedPreferences = getSharedPreferences("COLLECTOR_APP", Context.MODE_PRIVATE)
        title = getString(R.string.app_name)
        val btnShowCollection : Button = findViewById(R.id._btnShowCollection)
        val btnAddToList : Button = findViewById(R.id._btnAddToList)
        val gameTitle : EditText = findViewById(R.id._etTitle)
        val gameDesc : EditText = findViewById(R.id._etDescription)
        val gameplayerCount : EditText = findViewById(R.id._etPlayercount)
        val cardGame : SwitchCompat = findViewById(R.id._swCardGame)
        val diceGame : SwitchCompat = findViewById(R.id._swDiceGame)
        val RPG : SwitchCompat = findViewById(R.id._swRPG)
        val boardGame : SwitchCompat = findViewById(R.id._swBoardGame)

        resetArray()
        setImages()
        val editor= sharedPreference.edit()



        //SHOWS COLLECTION LIST
        btnShowCollection.setOnClickListener{
            val collectionIntent = Intent(this, CollectionListAct::class.java)
            Log.d("arrayCheck", listOfGames.toString())
            collectionIntent.putExtra("gameInfo", game.Title)
            startActivity(collectionIntent)

        }

        //ADDS GAME TO LIST
        btnAddToList.setOnClickListener {
            game.Title = gameTitle.text.toString().toUpperCase(Locale.ROOT)
            game.Desc = gameDesc.text.toString()
            game.PlayerCount = gameplayerCount.text.toString()
            if(gameTitle.text.isEmpty()){
                gameTitle.error = getString(R.string.str_error)
                gameTitle.isFocusable = true
            }
            else if(gameplayerCount.text.isEmpty()){
                gameplayerCount.error = getString(R.string.str_error)
                gameplayerCount.isFocusable = true
            }
            else{
                getCategory(cardGame.isChecked, diceGame.isChecked,RPG.isChecked, boardGame.isChecked)
                listOfGames.add(game.toString())

                Toast.makeText(applicationContext, getString(R.string.str_game_added_to_list), Toast.LENGTH_LONG).show()
                //THIS ISN'T SORTING CORRECTLY
                listOfGames.sortBy { game.Title }


                for(i in listOfGames){
                Log.d("listSort", i)}
                editor.putString(game.Title, game.toString())

            }
            if(gameTitle.text.toString() == "NCC-1701"){
                //can clear SP here
                editor.clear()
            }

            editor.apply()

            for(i in listOfGames){
                Log.d("itemArrayList", i)
            }

        }


    }

    @Override
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        //Saves current game state
        savedInstanceState.putParcelable("myGames", game)
        super.onSaveInstanceState(savedInstanceState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        game = savedInstanceState.getParcelable("myGames")!!

        super.onRestoreInstanceState(savedInstanceState)

    }
    //SHOWS OPTIONS MENU
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val name = getString(R.string.menu_name)
        val version = getString(R.string.menu_app_version)
        val info = getString(R.string.menu_app_info)
        val shoutOuts = getString(R.string.menu_shoutouts) + "\n\n"+getString(R.string.menu_shout1) + "\n\n"+getString(R.string.menu_shout2)
        when(item.itemId){
            R.id._menuAbout ->{ val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.menu_about)
                builder.setMessage("$name\n$version\n$info\n\n$shoutOuts")
                val alertDialog : AlertDialog = builder.create()
                alertDialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //PRIVATE FUNCTIONS
    private fun setImages(){
        val cardGame : SwitchCompat = findViewById(R.id._swCardGame)
        val diceGame : SwitchCompat = findViewById(R.id._swDiceGame)
        val RPG : SwitchCompat = findViewById(R.id._swRPG)
        val boardGame : SwitchCompat = findViewById(R.id._swBoardGame)
        val imageView : ImageView = findViewById(R.id._imgView)

        cardGame.setOnCheckedChangeListener { switchView, _ ->
            if (switchView?.isChecked == true) {
                Log.d("switchCard", "cardGame is checked")
                imageView.visibility = VISIBLE
                imageView.setImageResource(R.drawable.card_game)

            } else {
                Log.d("switchCard", "switch is NOT checked")
                imageView.visibility = View.INVISIBLE

            }
        }

        diceGame.setOnCheckedChangeListener { switchView, _ ->
            if (switchView?.isChecked == true) {
                Log.d("switchDice", "cardGame is checked")
                imageView.visibility = VISIBLE
                imageView.setImageResource(R.drawable.dice_game)

            } else {
                Log.d("switchDice", "switch is NOT checked")
                imageView.visibility = View.INVISIBLE

            }
        }

        RPG.setOnCheckedChangeListener { switchView, _ ->
            if (switchView?.isChecked == true) {
                Log.d("switchDice", "cardGame is checked")
                imageView.visibility = VISIBLE
                imageView.setImageResource(R.drawable.rpg)

            } else {
                Log.d("switchDice", "switch is NOT checked")
                imageView.visibility = View.INVISIBLE
                imageView.setImageResource(R.drawable.dice_game)

            }
        }

        boardGame.setOnCheckedChangeListener { switchView, _ ->
            if (switchView?.isChecked == true) {
                Log.d("switchDice", "cardGame is checked")
                imageView.visibility = VISIBLE
                imageView.setImageResource(R.drawable.board_game)

            } else {
                Log.d("switchDice", "switch is NOT checked")
                imageView.visibility = View.INVISIBLE

            }
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



