package com.titin.pendientes.domain.use_case

import com.titin.pendientes.data.local.ToDoEntity
import com.titin.pendientes.domain.repository.ToDoRepository
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(toDoUIModel: ToDoUIModel) {

        return repository.deleteToDo(
            toDoEntity = ToDoEntity(
                id = toDoUIModel.id,
                name = toDoUIModel.name,
                priority = toDoUIModel.priority,
                isDone = toDoUIModel.isDone,
                timestamp = toDoUIModel.timestamp
            )
        )
    }
}