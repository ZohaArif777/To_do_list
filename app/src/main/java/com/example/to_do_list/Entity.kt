package com.example.to_do_list


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
data class Entity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val priority: String
)
