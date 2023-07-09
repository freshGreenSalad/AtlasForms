package com.example.atlasforms.Di

import com.example.atlasforms.common.data.Room.*
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
    fun provideQuestionDao(database: AtlasDatabase): AnswerFormDao = database.answerFormDao()

}