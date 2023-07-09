package com.example.atlasforms.common.data.Room

import androidx.room.*
import com.example.atlasforms.common.domain.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg plant: Question)

    @Query("SELECT * FROM Question")
    fun getall(): Flow<List<Question>>

    @Update
    fun updatePeople (vararg: Question)

    @Delete
    fun deletePeople (plant: Question)
}