package com.titin.pendientes.domain.use_case

import com.titin.pendientes.data.local.ToDoEntity
import com.titin.pendientes.domain.repository.ToDoRepository
import java.util.Date
import javax.inject.Inject

class InsertOrUpdateToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(
        name: String,
        isInsert: Boolean,
        id: Int?,
        priority: String,
        date: Date,
        isDone: Boolean
    ) {
        if (isInsert) {
            repository.insertToDo(
                ToDoEntity(
                    name = name,
                    priority = priority,
                    timestamp = date,
                    isDone = isDone
                )
            )
        } else {
            repository.updateToDo(
                ToDoEntity(
                    id = id ?: 0,
                    name = name,
                    priority = priority,
                    timestamp = date,
                    isDone = isDone
                    )
                )
            }
        }
    }
