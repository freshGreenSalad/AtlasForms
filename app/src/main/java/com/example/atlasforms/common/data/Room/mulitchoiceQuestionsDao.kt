package com.example.atlasforms.common.data.Room

import androidx.room.*
import com.example.atlasforms.common.domain.OfMultiChoiceQuestions
import kotlinx.coroutines.flow.Flow

@Dao
interface mulitchoiceQuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg plant: OfMultiChoiceQuestions)

    @Query("SELECT * FROM OfMultiChoiceQuestions")
    fun getall(): Flow<List<OfMultiChoiceQuestions>>

    @Update
    fun updatePeople (vararg: OfMultiChoiceQuestions)

    @Delete
    fun deletePeople (plant: OfMultiChoiceQuestions)
}