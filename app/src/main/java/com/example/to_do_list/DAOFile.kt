package com.example.to_do_list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query

@Dao
interface DAOFile {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from To_Do")
    suspend fun deleteAll()

    @Query("Select * from To_Do")
    suspend fun getTasks(): List<Entity>
}
