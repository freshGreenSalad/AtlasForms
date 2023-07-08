package com.example.atlasforms.common.domain

data class Form(
    val dateCreated: String,
    val email: String,
    val id: Int,
    val name: String,
    val questionList: List<Question>
)

data class Question(
    val id: Int,
    val listOfMultiChoiceQuestions: List<OfMultiChoiceQuestions>,
    val question: String,
    val questionNumber: Int,
    val questionType: Int
)

data class OfMultiChoiceQuestions(
    val id: Int,
    val question: String
)