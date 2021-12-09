package com.dev.quiz.math

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dev.quiz.math.databinding.ActivityQuizBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val questionAdapter by lazy { QuestionAdapter() }
    private var questions = generateQuestionLvl0()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var currentPosition = 0
    private var point = 0
    private var currentLevel = 0

    private var analyticsStartTime = System.currentTimeMillis()
    private val analyticsDurationSeconds: Long
        get() = (System.currentTimeMillis() - analyticsStartTime) / 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAnalytics = Firebase.analytics

        binding.vpQuestion.isUserInputEnabled = false
        binding.vpQuestion.adapter = questionAdapter
        questionAdapter.questions = questions

        binding.btnSubmit.setOnClickListener {
            val answear = binding.etAnswear.text.toString()
            if (answear.isNotEmpty()) {
                val answ = answear.toInt()
                if (answ == questions[currentPosition].answerKey) point += 10
                binding.tvPoint.text = "$point"

                firebaseAnalytics.logEvent("time_to_answer") {
                    param("level", currentLevel.toLong())
                    param("point", point.toLong())
                    param("duration", analyticsDurationSeconds)
                }

                analyticsStartTime = System.currentTimeMillis()

                if (currentPosition == 9) {
                    if (currentLevel < 2) showAlertDialog("Next Level", "Do you want to continue to the next level?", "Let's do it", "No, Thanks")
                    else showAlertDialog("Quiz Finish", "Congratulations, you got $point points", "Play again", "Amazing")
                    currentPosition = 0
                } else {
                    currentPosition++
                    binding.vpQuestion.currentItem = currentPosition
                    binding.tvCurrentQuestion.text = "${currentPosition + 1}/${questions.size}"
                }
            }
        }

        binding.vpQuestion.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.etAnswear.setText("")
            }
        })
    }

    private fun showAlertDialog(
        title: String,
        message: String,
        positif: String,
        negatif: String
    ) {
        val alert = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(negatif) { dialog, _ ->
                dialog.dismiss()
                firebaseAnalytics.logEvent("surrender") {
                    param("level", currentLevel.toLong())
                    param("point", point.toLong())
                }

                if (currentLevel == 2) onBackPressed()
                else {
                    point = 0
                    currentLevel = 0
                    questions = generateQuestionLvl0()
                    questionAdapter.questions = questions
                    binding.vpQuestion.currentItem = currentPosition
                    binding.tvCurrentQuestion.text = "${currentPosition + 1}/${questions.size}"
                    binding.tvLevel.text = "$currentLevel"
                    binding.tvPoint.text = "$point"
                }
            }
            .setPositiveButton(positif) { dialog, _ ->
                dialog.dismiss()
                firebaseAnalytics.logEvent("fight") {
                    param("level", currentLevel.toLong())
                    param("point", point.toLong())
                }

                if (currentLevel == 2) {
                    point = 0
                    currentLevel = 0
                    questions = generateQuestionLvl0()
                    binding.tvPoint.text = "$point"
                } else {
                    currentLevel++
                    if (currentLevel == 1) questions = generateQuestionLvl1()
                    if (currentLevel == 2) questions = generateQuestionLvl2()
                }
                binding.tvLevel.text = "$currentLevel"
                questionAdapter.questions = questions
                binding.vpQuestion.currentItem = currentPosition
                binding.tvCurrentQuestion.text = "${currentPosition + 1}/${questions.size}"
            }

        alert.show()
    }
}