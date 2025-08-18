package com.example.layouteldenring

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        // Botão de voltar
        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Botões de linguagem
        val buttonEng: Button = findViewById(R.id.buttonEng)
        val buttonPtbr: Button = findViewById(R.id.buttonPtbr)

        // Função para aplicar estilo de selecionado/não selecionado
        fun selectLanguage(selected: Button, other: Button) {
            // Selecionado -> preto
            selected.backgroundTintList =
                ContextCompat.getColorStateList(this, android.R.color.darker_gray)
            selected.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))
            selected.setTextColor(ContextCompat.getColor(this, android.R.color.white))

            // Não selecionado -> transparente
            other.backgroundTintList =
                ContextCompat.getColorStateList(this, android.R.color.transparent)
            other.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
            other.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }

        // Ações de clique
        buttonEng.setOnClickListener {
            selectLanguage(buttonEng, buttonPtbr)
            Toast.makeText(this, "Idioma alterado para Inglês", Toast.LENGTH_SHORT).show()
        }

        buttonPtbr.setOnClickListener {
            selectLanguage(buttonPtbr, buttonEng)
            Toast.makeText(this, "Idioma alterado para Português", Toast.LENGTH_SHORT).show()
        }

        // Controle de volume
        val seekBarSound: SeekBar = findViewById(R.id.seekBarSound)
        seekBarSound.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
