package com.example.layouteldenring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class GameSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)

        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        val buttonImageGame: Button = findViewById(R.id.buttonImageGame)
        val buttonDescriptionGame: Button = findViewById(R.id.buttonDescriptionGame)

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonImageGame.setOnClickListener {
            val intent = Intent(this, ImageGameActivity::class.java)
            startActivity(intent)
        }

        buttonDescriptionGame.setOnClickListener {
            val intent = Intent(this, DescriptionGameActivity::class.java)
            startActivity(intent)
        }
    }
}
