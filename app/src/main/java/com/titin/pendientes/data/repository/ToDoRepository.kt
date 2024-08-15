package com.titin.pendientes.data.repository

import com.titin.pendientes.data.local.ToDoDao
import com.titin.pendientes.data.local.ToDoEntity
import com.titin.pendientes.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {
    override  fun getAllToDoList(): Flow<List<ToDoEntity>> {
        return dao.getAllToDo()
    }

    override fun getToDoByPriority(priority: String): Flow<List<ToDoEntity>> {
        return dao.getToDoByPriority(priority)
    }

    override suspend fun insertToDo(toDo: ToDoEntity) {
        dao.insertToDo(toDo = toDo)
    }

    override suspend fun getToDoById(id: Int): ToDoEntity {
        return dao.getToDoById(id = id)
    }

    override suspend fun deleteToDo(toDoEntity: ToDoEntity) {
        dao.deleteToDo(toDo = toDoEntity)
    }

    override suspend fun updateToDo(toDo: ToDoEntity) {
        dao.updateToDo(toDo = toDo)
    }
}