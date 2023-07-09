package com.example.atlasforms.common.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.atlasforms.common.domain.*

@Database(
    entities = [Form::class, AnswerForm::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FormConverter::class, )
abstract class AtlasDatabase: RoomDatabase(){
    abstract fun formDao(): FormDao
    abstract fun answerFormDao(): AnswerFormDao
}