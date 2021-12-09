package com.dev.quiz.math

fun generateQuestion(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    questions.addAll(generateQuestionLvl0())
    questions.addAll(generateQuestionLvl1())
    questions.addAll(generateQuestionLvl2())
    return questions
}

fun generateQuestionLvl0(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..10) {
        val numb1 = (1..100).random()
        val numb2 = (1..100).random()
        val randomOperator = "+-".random()

        val question = "$numb1 $randomOperator $numb2"
        val answerKey = when (randomOperator) {
            '+' -> numb1 + numb2
            '-' -> numb1 - numb2
            else -> 0
        }
        questions.add(QuestionData(id, question, answerKey, 0))
    }

    return questions
}

fun generateQuestionLvl1(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..10) {
        val numb1 = (1..100).random()
        val numb2 = (1..100).random()
        val numb3 = (1..100).random()
        val randomOperator1 = "+-".random()
        val randomOperator2 = "+-".random()

        val question = "$numb1 $randomOperator1 $numb2 $randomOperator2 $numb3"
        val answerKey = when (randomOperator1) {
            '+' -> when (randomOperator2) {
                '+' -> numb1 + numb2 + numb3
                '-' -> numb1 + numb2 - numb3
                else -> 0
            }
            '-' -> when (randomOperator2) {
                '+' -> numb1 - numb2 + numb3
                '-' -> numb1 - numb2 - numb3
                else -> 0
            }
            else -> 0
        }
        questions.add(QuestionData(id, question, answerKey, 1))
    }

    return questions
}

fun generateQuestionLvl2(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..10) {
        val numb1 = (1..100).random()
        val numb2 = (1..100).random()
        val numb3 = (1..100).random()
        val numb4 = (1..100).random()
        val randomOperator1 = "+-".random()
        val randomOperator2 = "+-".random()
        val randomOperator3 = "+-".random()

        val question = "$numb1 $randomOperator1 $numb2 $randomOperator2 $numb3 $randomOperator3 $numb4"
        val answerKey = when (randomOperator1) {
            '+' -> when (randomOperator2) {
                '+' -> when (randomOperator3) {
                    '+' -> numb1 + numb2 + numb3 + numb4
                    '-' -> numb1 + numb2 + numb3 - numb4
                    else -> 0
                }
                '-' -> when (randomOperator3) {
                    '+' -> numb1 + numb2 - numb3 + numb4
                    '-' -> numb1 + numb2 - numb3 - numb4
                    else -> 0
                }
                else -> 0
            }
            '-' -> when (randomOperator2) {
                '+' -> when (randomOperator3) {
                    '+' -> numb1 - numb2 + numb3 + numb4
                    '-' -> numb1 - numb2 + numb3 - numb4
                    else -> 0
                }
                '-' -> when (randomOperator3) {
                    '+' -> numb1 - numb2 - numb3 + numb4
                    '-' -> numb1 - numb2 - numb3 - numb4
                    else -> 0
                }
                else -> 0
            }
            else -> 0
        }
        questions.add(QuestionData(id, question, answerKey, 2))
    }

    return questions
}