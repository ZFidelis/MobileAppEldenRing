package com.example.layouteldenring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ImageGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_game)

        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        val buttonAlternative1: Button = findViewById(R.id.buttonAlternative1)
        val buttonAlternative2: Button = findViewById(R.id.buttonAlternative2)
        val buttonAlternative3: Button = findViewById(R.id.buttonAlternative3)
        val buttonAlternative4: Button = findViewById(R.id.buttonAlternative4)

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonAlternative1.setOnClickListener {
            val intent = Intent(this, LastActivity::class.java)
            startActivity(intent)
        }
        buttonAlternative2.setOnClickListener {
            val intent = Intent(this, LastActivity::class.java)
            startActivity(intent)
        }
        buttonAlternative3.setOnClickListener {
            val intent = Intent(this, LastActivity::class.java)
            startActivity(intent)
        }
        buttonAlternative4.setOnClickListener {
            val intent = Intent(this, LastActivity::class.java)
            startActivity(intent)
        }
    }
}
