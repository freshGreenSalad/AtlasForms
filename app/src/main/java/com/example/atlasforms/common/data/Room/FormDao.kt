package com.example.atlasforms.common.data.Room

import androidx.room.*
import com.example.atlasforms.common.domain.Form
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg plant: Form)

    @Query("SELECT * FROM Form")
    fun getall(): Flow<List<Form>>

    @Update
    fun updatePeople (vararg: Form)

    @Delete
    fun deletePeople (plant: Form)
}