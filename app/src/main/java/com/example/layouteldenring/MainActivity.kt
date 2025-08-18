package com.example.layouteldenring


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.buttonPlay)
        val buttonConfig: ImageButton = findViewById(R.id.buttonSettings)

        buttonStart.setOnClickListener {
            val intent = Intent(this, GameSelectionActivity::class.java)
            startActivity(intent)
        }

        buttonConfig.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }
    }
}
