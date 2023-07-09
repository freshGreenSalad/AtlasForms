package com.example.atlasforms.common.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.atlasforms.common.domain.Form
import com.example.atlasforms.common.domain.FormConverter
import com.example.atlasforms.common.domain.OfMultiChoiceQuestions
import com.example.atlasforms.common.domain.Question

@Database(
    entities = [Form::class, Question::class, OfMultiChoiceQuestions::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FormConverter::class, )
abstract class AtlasDatabase: RoomDatabase(){
    abstract fun formDao(): FormDao
    abstract fun questionDao(): QuestionDao
    abstract fun multiChoiceQuestionDao(): mulitchoiceQuestionsDao
}