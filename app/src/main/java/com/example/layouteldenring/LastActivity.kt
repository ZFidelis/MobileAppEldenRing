package com.example.layouteldenring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class LastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)

        val buttonMainMenu: Button = findViewById(R.id.buttonMainMenu)
        val buttonPlayAgain: Button = findViewById(R.id.buttonPlayAgain)


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
