package com.example.highorlow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonAbout = findViewById<Button>(R.id.buttonAbout)
        buttonStart.setOnClickListener{

            val intent = Intent(this, GameBoard::class.java)
            startActivity(intent)
        }
        buttonAbout.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)

        }

    }
}