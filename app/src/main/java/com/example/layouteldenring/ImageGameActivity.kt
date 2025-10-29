package com.example.layouteldenring

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageGameActivity : AppCompatActivity() {

    data class Question(
        val imageResId: Int,
        val answer: String,
        val alternatives: List<String>
    )

    private lateinit var allQuestions: List<Question>
    private lateinit var remainingQuestions: MutableList<Question>
    private lateinit var thisQuestion: Question
    private lateinit var alternativeButtons: List<Button>

    private lateinit var ivQuestionImage: ImageView
    private lateinit var buttonAlternative1: Button
    private lateinit var buttonAlternative2: Button
    private lateinit var buttonAlternative3: Button
    private lateinit var buttonAlternative4: Button

    var totalQuestions: Int = 0
    var rightResponses: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_game)

        ivQuestionImage = findViewById(R.id.iv_question_image)
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

    private fun loadQuestions(): List<Question> {
        val questionList = mutableListOf<Question>()

        val answers = listOf(
            getString(R.string.name_malenia),
            getString(R.string.name_radahn),
            getString(R.string.name_godrick),
            getString(R.string.name_rivers_of_blood),
            getString(R.string.name_blaidd),
            getString(R.string.name_ranni),
            getString(R.string.name_morgott),
            getString(R.string.name_hoarah_loux)
        )

        val allNames = answers

        val imageResIds = resources.obtainTypedArray(R.array.image_questions)

        for (i in 0 until imageResIds.length()) {
            val imageResId = imageResIds.getResourceId(i, -1)
            val answer = answers[i]

            val wrongAlternatives = allNames.filter { it != answer }.shuffled().take(3)
            val allAlternatives = (wrongAlternatives + answer).shuffled()

            if (imageResId != -1) {
                questionList.add(
                    Question(
                        imageResId = imageResId,
                        answer = answer,
                        alternatives = allAlternatives
                    )
                )
            }
        }

        imageResIds.recycle()
        return questionList
    }

    private fun startGame() {
        allQuestions = loadQuestions()
        remainingQuestions = allQuestions.toMutableList()
        totalQuestions = 0
        rightResponses = 0
        nextQuestion()
    }

    private fun nextQuestion() {
        if (remainingQuestions.isNotEmpty()) {
            totalQuestions++
            thisQuestion = remainingQuestions.random().also { remainingQuestions.remove(it) }

            ivQuestionImage.setImageResource(thisQuestion.imageResId)
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
}
