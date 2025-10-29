package com.example.layouteldenring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)

        val buttonMainMenu: Button = findViewById(R.id.buttonMainMenu)
        val buttonPlayAgain: Button = findViewById(R.id.buttonPlayAgain)
        val textViewScore: TextView = findViewById(R.id.title)
        val message: TextView = findViewById(R.id.subtitle)

        buttonPlayAgain.setText(R.string.play_again_btn)
        buttonMainMenu.setText(R.string.main_menu_btn)

        val total = intent.getIntExtra("totalQuestions", 0)
        val right = intent.getIntExtra("rightResponses", 0)

        textViewScore.text = getString(R.string.score_text, right, total)

        if (total > right) {
            message.text = getString(R.string.end_game_no_congrat)
            message.setTextColor(getColor(R.color.incorrectColor))
        } else {
            message.text = getString(R.string.end_game_congrat)
            message.setTextColor(getColor(R.color.correctColor))
        }

        buttonMainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this, GameSelectionActivity::class.java)
            startActivity(intent)
        }
    }
}
