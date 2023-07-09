package com.example.atlasforms.common.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity
data class AnswerForm(
    @PrimaryKey val id: Int = 0,
    val dateCreated: String = "",
    val email: String = "",
    val name: String = "",
    val isSent: Boolean = false,
    val questionList: List<AnswerQuestion> = emptyList<AnswerQuestion>()
)

@Entity
@Serializable
data class AnswerQuestion(
    @PrimaryKey val id: Int,
    val listOfMultiChoiceQuestions: List<OfMultiChoiceQuestions>,
    val chosenMultiChoiceAnswer: String = "",
    val question: String,
    val answer:String,
    val isAnswered: Boolean = false,
    val questionNumber: Int,
    val questionType: Int
)

class AnswerFormConverter {
    @TypeConverter
    fun fromQuesitonList(listQuestion: List<AnswerQuestion?>?): String? {
        return Json.encodeToString(listQuestion)
    }

    @TypeConverter
    fun toQuestionList(questionList: String?): List<AnswerQuestion> {
        if (questionList == null) {
            return emptyList<AnswerQuestion>()
        }
        return Json.decodeFromString<List<AnswerQuestion>>(questionList)
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