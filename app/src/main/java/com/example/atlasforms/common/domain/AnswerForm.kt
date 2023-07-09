package com.example.atlasforms.common.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AnswerForm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateCreated: String = "",
    val email: String = "",
    val name: String = "",
    val isSent: Boolean = false,
    val questionList: List<AnswerQuestion> = emptyList<AnswerQuestion>()
)

@Entity
@Serializable
data class AnswerQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val listOfMultiChoiceQuestions: List<OfMultiChoiceQuestions>,
    val chosenMultiChoiceAnswer: String = "",
    val question: String,
    val answer:String,
    val isAnswered: Boolean = false,
    val questionNumber: Int,
    val questionType: QuestionType
)