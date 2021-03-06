package com.keren.tomer.minesweeper

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    companion object {
        const val INTENT_WIDTH = "WIDTH"
        const val INTENT_HEIGHT = "HEIGHT"
        const val INTENT_MINES = "MINES"
        const val TAG = "GameActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
//        toasty_button.setOnClickListener { Toast.makeText(this, "Hello!", Toast.LENGTH_LONG).show() }
        //        gameGrid.adapter = GameAdapter(this)
        val width = intent.getIntExtra(INTENT_WIDTH, 15)
        val height = intent.getIntExtra(INTENT_HEIGHT, 15)
        val mines = intent.getIntExtra(INTENT_MINES, 15)
        val model = ViewModelProviders.of(this, GameViewModelFactory(width, height, mines)).get(GameViewModel::class.java)
        model.winnState.observe(this, Observer {
            game_zoom.engine.zoomTo(1.0F, true)
            GameEndDialog.newInstance(it!!).show(supportFragmentManager,"game end dialog")
        })
        game_board.addGame(model)
//        game = Game(width = width,height=height,amountOfMines = mines)

    }
}
fun Activity.restart(){
    startActivity(intent)
    finish()
}

