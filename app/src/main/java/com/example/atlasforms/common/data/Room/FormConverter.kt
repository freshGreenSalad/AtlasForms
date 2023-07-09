package com.example.atlasforms.common.data.Room

import androidx.room.TypeConverter
import com.example.atlasforms.common.domain.AnswerQuestion
import com.example.atlasforms.common.domain.OfMultiChoiceQuestions
import com.example.atlasforms.common.domain.Question
import com.example.atlasforms.common.domain.QuestionType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FormConverter {

    @TypeConverter
    fun fromQuestionType(questionType: QuestionType): Int {
        return questionType.value
    }

    @TypeConverter
    fun toQuestionType(value: Int): QuestionType {
        return QuestionType.fromInt(value)
    }


    @TypeConverter
    fun fromAnswerQuestionList(listQuestion: List<AnswerQuestion?>?): String? {
        return Json.encodeToString(listQuestion)
    }

    @TypeConverter
    fun toAnswerQuestionList(questionList: String?): List<AnswerQuestion> {
        if (questionList == null) {
            return emptyList<AnswerQuestion>()
        }
        return Json.decodeFromString<List<AnswerQuestion>>(questionList)
    }
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