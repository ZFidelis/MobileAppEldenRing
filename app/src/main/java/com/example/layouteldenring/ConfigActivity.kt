package com.example.layouteldenring

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonEng: Button = findViewById(R.id.buttonEng)
        val buttonPtbr: Button = findViewById(R.id.buttonPtbr)

        val textLanguage: TextView = findViewById(R.id.textLanguage)
        val textSound: TextView = findViewById(R.id.textSound)

        textLanguage.setText(R.string.language)
        textSound.setText(R.string.sound)

        fun selectLanguage(selected: Button, other: Button) {
            selected.backgroundTintList =
                ContextCompat.getColorStateList(this, android.R.color.black)
            selected.setTextColor(ContextCompat.getColor(this, android.R.color.white))

            other.backgroundTintList =
                ContextCompat.getColorStateList(this, android.R.color.transparent)
            other.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }

        fun changeAppLanguage(languageCode: String) {
            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(languageCode)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }

        buttonEng.setOnClickListener {
            selectLanguage(buttonEng, buttonPtbr)
            changeAppLanguage("en")
            Toast.makeText(this, "Language changed to English", Toast.LENGTH_SHORT).show()
        }

        buttonPtbr.setOnClickListener {
            selectLanguage(buttonPtbr, buttonEng)
            changeAppLanguage("pt")
            Toast.makeText(this, "Idioma alterado para Português", Toast.LENGTH_SHORT).show()
        }

        val currentLang = AppCompatDelegate.getApplicationLocales()[0]?.language ?: Locale.getDefault().language
        if (currentLang == "pt") {
            selectLanguage(buttonPtbr, buttonEng)
        } else {
            selectLanguage(buttonEng, buttonPtbr)
        }

        // --> CONTROLE DE VOLUME <-- //
        val seekBarSound: SeekBar = findViewById(R.id.seekBarSound)
        seekBarSound.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
