package com.example.layouteldenring

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Question(
    val description: String,
    val answer: String,
    val alternatives: List<String>
)

class DescriptionGameActivity : AppCompatActivity() {

    private lateinit var allQuestions: List<Question>
    private lateinit var remainingQuestions: MutableList<Question>
    private lateinit var thisQuestion: Question
    private lateinit var alternativeButtons: List<Button>
    
    private lateinit var descriptionTextView: TextView
    private lateinit var buttonAlternative1: Button
    private lateinit var buttonAlternative2: Button
    private lateinit var buttonAlternative3: Button
    private lateinit var buttonAlternative4: Button

    var totalQuestions: Int = 0
    var rightResponses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_game)

        descriptionTextView = findViewById(R.id.title)
        val buttonBack: ImageButton = findViewById(R.id.buttonBack)
        buttonAlternative1 = findViewById(R.id.buttonAlternative1)
        buttonAlternative2 = findViewById(R.id.buttonAlternative2)
        buttonAlternative3 = findViewById(R.id.buttonAlternative3)
        buttonAlternative4 = findViewById(R.id.buttonAlternative4)

        alternativeButtons = listOf(buttonAlternative1, buttonAlternative2, buttonAlternative3, buttonAlternative4)
        startGame()

        buttonBack.setOnClickListener {
            finish()
        }

        buttonAlternative1.setOnClickListener { checkResponse(buttonAlternative1.text.toString()) }
        buttonAlternative2.setOnClickListener { checkResponse(buttonAlternative2.text.toString()) }
        buttonAlternative3.setOnClickListener { checkResponse(buttonAlternative3.text.toString()) }
        buttonAlternative4.setOnClickListener { checkResponse(buttonAlternative4.text.toString()) }
    }

    private fun startGame() {
        allQuestions = loadQuestions()
        remainingQuestions = allQuestions.toMutableList()

        nextQuestion()
    }

    private fun nextQuestion() {
        if (remainingQuestions.isNotEmpty()) {
            totalQuestions++
            thisQuestion = remainingQuestions.random()
            remainingQuestions.remove(thisQuestion)

            descriptionTextView.text = thisQuestion.description
            buttonAlternative1.text = thisQuestion.alternatives[0]
            buttonAlternative2.text = thisQuestion.alternatives[1]
            buttonAlternative3.text = thisQuestion.alternatives[2]
            buttonAlternative4.text = thisQuestion.alternatives[3]
        } else {
            endGame()
        }
    }

    private fun checkResponse(selectedResponse: String) {
        alternativeButtons.forEach { it.isClickable = false }

        val correctButton = alternativeButtons.find { it.text == thisQuestion.answer }
        val selectedButton = alternativeButtons.find { it.text == selectedResponse }

        if (selectedResponse == thisQuestion.answer) {
            selectedButton?.setBackgroundColor(getColor(R.color.correctColor))
            rightResponses++
        } else {
            selectedButton?.setBackgroundColor(getColor(R.color.incorrectColor))
            correctButton?.setBackgroundColor(getColor(R.color.correctColor))
        }

        android.os.Handler(mainLooper).postDelayed({
            alternativeButtons.forEach { button ->
                button.setBackgroundColor(getColor(R.color.primaryButtonColor))
                button.isClickable = true
            }
            nextQuestion()
        }, 1500)
    }

    private fun endGame() {

        val intent = Intent(this, LastActivity::class.java)

        intent.putExtra("totalQuestions", totalQuestions)
        intent.putExtra("rightResponses", rightResponses)

        startActivity(intent)
        finish()
    }

    private fun loadQuestions(): List<Question> {
        val allNames = listOf(
            getString(R.string.name_malenia),
            getString(R.string.name_radahn),
            getString(R.string.name_godrick),
            getString(R.string.name_rivers_of_blood),
            getString(R.string.name_blaidd),
            getString(R.string.name_ranni),
            getString(R.string.name_morgott),
            getString(R.string.name_hoarah_loux)
        )

        val questionAnswer = listOf(
            Pair(R.string.desc_malenia, R.string.name_malenia),
            Pair(R.string.desc_radahn, R.string.name_radahn),
            Pair(R.string.desc_godrick, R.string.name_godrick),
            Pair(R.string.desc_rivers_of_blood, R.string.name_rivers_of_blood),
            Pair(R.string.desc_blaidd, R.string.name_blaidd)
        )


        return questionAnswer.map { (descriptionId, answerId) ->
            val description = getString(descriptionId)
            val answer = getString(answerId)

            val wrongAlternatives = allNames.filter { it != answer }

            val wrongAlternativesToUse = wrongAlternatives.shuffled().take(3)

            val allAlternatives = (wrongAlternativesToUse + answer).shuffled()

            Question(
                description = description,
                answer = answer,
                alternatives = allAlternatives
            )
        }
    }
}
