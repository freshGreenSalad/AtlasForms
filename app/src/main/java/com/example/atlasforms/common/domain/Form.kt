package com.example.atlasforms.common.domain

import androidx.room.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity
data class Form(
    @PrimaryKey val id: Int = 0,
    val dateCreated: String = "",
    val email: String = "",
    val name: String = "",
    val questionList: List<Question> = emptyList<Question>()
)

@Entity
@Serializable
data class Question(
    @PrimaryKey val id: Int,
    val listOfMultiChoiceQuestions: List<OfMultiChoiceQuestions>,
    val question: String,
    val questionNumber: Int,
    val questionType: Int
)

@Entity
@Serializable
data class OfMultiChoiceQuestions(
    @PrimaryKey val id: Int,
    val question: String
)
