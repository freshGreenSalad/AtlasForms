package com.example.atlasforms.common.domain

import androidx.room.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity
data class Form(
    @PrimaryKey val id: Int,
    val dateCreated: String,
    val email: String,
    val name: String,
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

class FormConverter {
    @TypeConverter
    fun fromQuesitonList(listQuestion: List<Question?>?): String? {
        return Json.encodeToString(listQuestion)
    }

    @TypeConverter
    fun toQuestionList(questionList: String?): List<Question> {
        if (questionList == null) {
            return emptyList<Question>()
        }
        return Json.decodeFromString<List<Question>>(questionList)
    }

    @TypeConverter
    fun fromMultiList(listQuestion: List<OfMultiChoiceQuestions?>?): String? {
        return Json.encodeToString(listQuestion)
    }

    @TypeConverter
    fun toMultiList(questionList: String?): List<OfMultiChoiceQuestions> {
        if (questionList == null) {
            return emptyList<OfMultiChoiceQuestions>()
        }
        return Json.decodeFromString<List<OfMultiChoiceQuestions>>(questionList)
    }
}