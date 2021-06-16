package com.example.rockpaperscissors

import android.os.*
import android.view.*
import kotlin.random.*
import android.widget.*
import android.graphics.*
import androidx.appcompat.app.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ROCK = 0
        const val PAPER = 1
        const val SCISSORS = 2

        const val COMPUTER = 1
        const val PLAYER = 2
        const val DRAW = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun rockButtonClicked(view: View) {
        buttonClicked(ROCK)
    }

    fun paperButtonClicked(view: View) {
        buttonClicked(PAPER)
    }

    fun scissorsButtonClicked(view: View) {
        buttonClicked(SCISSORS)
    }

    private fun buttonClicked(button: Int) {
        val hintTextView = findViewById<TextView>(R.id.hintTextView)
        if (hintTextView.visibility == View.VISIBLE) {
            hintTextView.visibility = View.INVISIBLE
        }

        val playerImageView = findViewById<ImageView>(R.id.playerImageView)
        val computerImageView = findViewById<ImageView>(R.id.computerImageView)

        val playerChoice = button
        val computerChoice = Random.nextInt(3)

        setImage(playerChoice, playerImageView)
        setImage(computerChoice, computerImageView)

        val playerPointsTextView = findViewById<TextView>(R.id.playerPointsTextView)
        val computerPointsTextView = findViewById<TextView>(R.id.computerPointsTextView)

        when (detectWinner(playerChoice, computerChoice)) {
            PLAYER -> {
                Toast.makeText(applicationContext,"Player won", Toast.LENGTH_SHORT).show()
                val playerPoints = playerPointsTextView.text.toString().toInt() + 1
                playerPointsTextView.text = playerPoints.toString()
                playerPointsTextView.setTextColor(Color.GREEN)
                computerPointsTextView.setTextColor(Color.BLACK)
            }

            COMPUTER -> {
                Toast.makeText(applicationContext,"Computer won", Toast.LENGTH_SHORT).show()
                val computerPoints = computerPointsTextView.text.toString().toInt() + 1
                computerPointsTextView.text = computerPoints.toString()
                computerPointsTextView.setTextColor(Color.GREEN)
                playerPointsTextView.setTextColor(Color.BLACK)
            }

            DRAW -> {
                Toast.makeText(applicationContext,"You drew", Toast.LENGTH_SHORT).show()
                playerPointsTextView.setTextColor(Color.YELLOW)
                computerPointsTextView.setTextColor(Color.YELLOW)
            }

        }
    }

    private fun setImage(button: Int, imageView: ImageView) {
        when (button) {
            ROCK -> imageView.setImageResource(R.drawable.rock)
            PAPER -> imageView.setImageResource(R.drawable.paper)
            SCISSORS -> imageView.setImageResource(R.drawable.scissors)
        }

        if (imageView.visibility == View.INVISIBLE) {
            imageView.visibility = View.VISIBLE
        }
    }

    private fun detectWinner(playerChoice: Int, computerChoice: Int): Int {
        if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
                (playerChoice == SCISSORS && computerChoice == PAPER) ||
                (playerChoice == PAPER && computerChoice == ROCK)) {
            return PLAYER
        }

        if ((computerChoice == ROCK && playerChoice == SCISSORS) ||
                (computerChoice == SCISSORS && playerChoice == PAPER) ||
                (computerChoice == PAPER && playerChoice == ROCK)) {
            return COMPUTER
        }

        return DRAW
    }

}
