package com.example.highorlow
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.os.CountDownTimer

class GameBoard : AppCompatActivity() {



    lateinit var scoreView : TextView
    lateinit var cardView : ImageView
    var card = 1
    var score = 0
    var hasStarted = false
    lateinit var timerView : TextView
    var backside = (R.drawable.backside)


    var timer = object: CountDownTimer(30000, 1000) {
         override fun onTick(millisUntilFinished: Long) {
             timerView.text = (millisUntilFinished/1000).toString()

         }
         override fun onFinish() {
             hasStarted = false
             timerView.text = "TIMES UP!"
         }
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_board)

        scoreView = findViewById(R.id.ScoreView)
        cardView = findViewById(R.id.cardView)
        timerView = findViewById(R.id.timesUpView)

        val buttonStartGameBoard = findViewById<Button>(R.id.buttonStartGame)
        buttonStartGameBoard.setOnClickListener {
                if(!hasStarted){
                    buttonStartGameBoard.text = "Restart Game"
                    timer.start()
                    hasStarted = true
                    nextCard()
                }else {
                    timer.cancel()
                    buttonStartGameBoard.text = "Start Game"
                    hasStarted = false
                    cardView.setImageResource(backside)
                    timerView.text = "30"
                    scoreView.text = "Score:"
                    score = 0

                }
        }
        val buttonHigher = findViewById<Button>(R.id.buttonHigher)

        buttonHigher.setOnClickListener {
            if (hasStarted){
                val previousCard = card
                nextCard()
                if (card > previousCard){
                    giveScore()
                }else{
                    takeScore()
                }
            }
        }
        val buttonLower = findViewById<Button>(R.id.buttonLower)
        buttonLower.setOnClickListener {
            if (hasStarted){
                val previousCard = card
                nextCard()
                if (card < previousCard){
                  giveScore()

                }else{
                    takeScore()
                }
            }
        }
        val returnButton = findViewById<Button>(R.id.buttonReturn)

        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun nextCard (){
        card = (0..52).random()
        cardView.setImageResource(Cards.cards[card])
    }
    fun giveScore(){
        score++
        scoreView.text = "Score:" + score.toString()
    }
    fun takeScore(){
        score --
        scoreView.text = "Score:" + score.toString()
    }
}