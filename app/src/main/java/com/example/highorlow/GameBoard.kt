package com.example.highorlow
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.os.CountDownTimer

class GameBoard : AppCompatActivity() {

 // diamond,club,heart,spade
    var cards = arrayOf(R.drawable.diamond2, R.drawable.club2,R.drawable.heart2,R.drawable.spade2,
     R.drawable.diamond3, R.drawable.club3,R.drawable.heart3,R.drawable.spade3,
     R.drawable.diamond4, R.drawable.club4,R.drawable.heart4,R.drawable.spade4,
     R.drawable.diamond5, R.drawable.club5,R.drawable.heart5,R.drawable.spade5,
     R.drawable.diamond6, R.drawable.club6,R.drawable.heart6,R.drawable.spade6,
     R.drawable.diamond7, R.drawable.club7,R.drawable.heart7,R.drawable.spade7,
     R.drawable.diamond8, R.drawable.club8,R.drawable.heart8,R.drawable.spade8,
     R.drawable.diamond9, R.drawable.club9,R.drawable.heart9,R.drawable.spade9,
     R.drawable.diamond10, R.drawable.club10,R.drawable.heart10,R.drawable.spade10,
     R.drawable.diamondjack, R.drawable.clubjack,R.drawable.heartjack,R.drawable.spadejack,
     R.drawable.diamondqueen, R.drawable.clubqueen,R.drawable.heartqueen,R.drawable.spadequeen,
     R.drawable.diamondking, R.drawable.clubking,R.drawable.heartking,R.drawable.spadeking,
     R.drawable.diamondace, R.drawable.clubace,R.drawable.heartace,R.drawable.spadeace)

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
                    scoreView.text = "0"
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
        cardView.setImageResource(cards[card])
    }
    fun giveScore(){
        score++
        scoreView.text = score.toString()
    }
    fun takeScore(){
        score --
        scoreView.text = score.toString()
    }
}