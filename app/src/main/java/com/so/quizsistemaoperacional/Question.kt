package com.so.quizsistemaoperacional

data class Question(
    val source: String,              // Ex: "CESPE / CNPQ / 2021"
    val text: String,                // Enunciado da questão
    val options: List<String>,       // Alternativas
    val correctAnswer: Int,          // Índice da resposta correta
    val type: QuestionType = QuestionType.MULTIPLE_CHOICE // Tipo de questão
)
