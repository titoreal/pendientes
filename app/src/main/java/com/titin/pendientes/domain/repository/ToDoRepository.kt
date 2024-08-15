package com.titin.pendientes.domain.repository

import com.titin.pendientes.data.local.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getAllToDoList(): Flow<List<ToDoEntity>>

    fun getToDoByPriority(priority: String): Flow<List<ToDoEntity>>

    suspend fun insertToDo(toDo: ToDoEntity)

    suspend fun getToDoById(id: Int): ToDoEntity

    suspend fun deleteToDo(toDoEntity: ToDoEntity)

    suspend fun updateToDo(toDo: ToDoEntity)
}