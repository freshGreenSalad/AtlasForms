package com.example.atlasforms.Di

import com.example.atlasforms.common.data.Room.AtlasDatabase
import com.example.atlasforms.common.data.Room.FormDao
import com.example.atlasforms.common.data.Room.QuestionDao
import com.example.atlasforms.common.data.Room.mulitchoiceQuestionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideFormDao(database: AtlasDatabase): FormDao = database.formDao()

    @Provides
    fun provideQuestionDao(database: AtlasDatabase): QuestionDao = database.questionDao()

    @Provides
    fun provideMultichoiceQuestionDaoDao(database: AtlasDatabase): mulitchoiceQuestionsDao = database.multiChoiceQuestionDao()

}