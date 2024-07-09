package com.example.to_do_list

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version=1)
abstract class myDb : RoomDatabase() {
    abstract fun dao():DAOFile
}