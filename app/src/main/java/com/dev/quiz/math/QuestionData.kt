package com.dev.quiz.math

data class QuestionData(
    val id: Int,
    val question: String,
    val answerKey: Int,
    val level: Int,
    val type:QuestionType
)

enum class QuestionType {
    BASIC_SUM, MEDIUM_SUM, MULTIPLICATION, COMPARATION
}
