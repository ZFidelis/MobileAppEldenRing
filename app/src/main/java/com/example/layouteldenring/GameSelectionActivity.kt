package com.example.layouteldenring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageButton

class GameSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)

        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        val buttonImageGame: Button = findViewById(R.id.buttonImageGame)
        val buttonDescriptionGame: Button = findViewById(R.id.buttonDescriptionGame)
        val title: TextView = findViewById(R.id.title)

        title.setText(R.string.game_selection_title)
        buttonImageGame.setText(R.string.image_game_btn)
        buttonDescriptionGame.setText(R.string.description_game_btn)

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
