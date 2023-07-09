package com.example.atlasforms.common.data.Room

import androidx.room.*
import com.example.atlasforms.common.domain.AnswerForm

@Dao
interface AnswerFormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg form: AnswerForm)

    @Query("SELECT * FROM AnswerForm")
    fun getall(): List<AnswerForm>

    @Update
    fun update (vararg: AnswerForm)

    @Query("DELETE FROM AnswerForm")
    fun nukeTable()
}