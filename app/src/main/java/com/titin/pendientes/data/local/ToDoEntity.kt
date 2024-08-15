package com.titin.pendientes.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    @ColumnInfo(name = "priority")
    val priority: String?,

    @ColumnInfo(name = "timestamp")
    val timestamp: Date
)