package com.example.atlasforms.Di

import android.content.Context
import androidx.room.Room
import com.example.atlasforms.common.data.Room.AtlasDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AtlasDatabase =
        Room.databaseBuilder(
            context,
            AtlasDatabase::class.java,
            "atlasDatabase"
        ).build()
}