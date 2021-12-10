package com.dev.quiz.math

fun generateQuestion(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    questions.addAll(generateQuestionLvl0())
    questions.addAll(generateQuestionLvl1())
    questions.addAll(generateQuestionLvl2())
    return questions
}

//Penjumlahan Puluhan
fun generateQuestionLvl0(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..2) {
        val numb1 = (10..99).random()
        val numb2 = (10..99).random()
        val randomOperator = "+-".random()

        val question = "$numb1 $randomOperator $numb2"
        val answerKey = when (randomOperator) {
            '+' -> numb1 + numb2
            '-' -> numb1 - numb2
            else -> 0
        }
        questions.add(QuestionData(id, question, answerKey, 0, QuestionType.BASIC_SUM))
    }

    return questions
}

//Penjumlahan Ratusan
fun generateQuestionLvl1(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..2) {
        val numb1 = (100..999).random()
        val numb2 = (100..999).random()
        val randomOperator1 = "+-".random()

        val question = "$numb1 $randomOperator1 $numb2"
        val answerKey = when (randomOperator1) {
            '+' -> numb1 + numb2
            '-' -> numb1 - numb2
            else -> 0
        }
        questions.add(QuestionData(id, question, answerKey, 1,QuestionType.MEDIUM_SUM))
    }

    return questions
}

//Perkalian dengan Puluhan
fun generateQuestionLvl2(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..2) {
        val numb1 = (1..10).random()
        val numb2 = (11..99).random()
        val operator1 = "*"

        val question = "$numb1 $operator1 $numb2"
        val answerKey = numb1 * numb2
        questions.add(QuestionData(id, question, answerKey, 2,QuestionType.MULTIPLICATION))
    }

    return questions
}

//Perbandingan Harga
fun generateQuestionLvl3(): List<QuestionData> {
    val questions = arrayListOf<QuestionData>()
    for (id in 1..2) {
        val jumlah1 = listOf(2, 4, 8).random()
        val harga1 = listOf(2000, 4000, 8000, 16000, 32000).random()
        val jumlah2 = listOf(16).random()
        val barang1 = "Pensil"
        val barang2 = "Pulpen"
        val operator = "+"
//2 pensil 16 pulpen = 2000. 1 pulpen = 16/2*2000 = 16000
        val question =
            "Jika $jumlah1 $barang1 = $jumlah2 $barang2 dengan harga 1 $barang1 seharga $harga1. Berapa harga $barang2?"
        val answerKey = jumlah1 * harga1 / jumlah2
        questions.add(QuestionData(id, question, answerKey, 3,QuestionType.COMPARATION))
    }

    return questions
}