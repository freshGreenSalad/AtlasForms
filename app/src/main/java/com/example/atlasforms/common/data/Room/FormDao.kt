package com.example.atlasforms.common.data.Room

import androidx.room.*
import com.example.atlasforms.common.domain.Form
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg form: Form)

    @Query("SELECT * FROM Form")
    fun getall(): List<Form>

    @Update
    fun updatePeople (vararg: Form)

    @Query("DELETE FROM Form")
    fun nukeTable()
}