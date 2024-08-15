package com.titin.pendientes.domain.mapper

import com.titin.pendientes.data.local.ToDoEntity
import com.titin.pendientes.domain.uimodel.ToDoUIModel

class ToDoEntityMapper {

    fun map(entity: ToDoEntity): ToDoUIModel {
        return entity.toUiModel()
    }

    private fun ToDoEntity.toUiModel() = ToDoUIModel(
        id = getId(),
        name = getName(),
        isDone = getIsDone(),
        priority = getPriority(),
        timestamp = getTimestamp(),
        priorityColor = getPriorityColor()
    )

    private fun ToDoEntity.getId() = id
    private fun ToDoEntity.getName() = name
    private fun ToDoEntity.getIsDone() = isDone
    private fun ToDoEntity.getPriority() = priority.orEmpty()
    private fun ToDoEntity.getTimestamp() = timestamp

    private fun ToDoEntity.getPriorityColor() = when (priority) {
        "baja" -> "#FFFFAB91"
        "urgente" -> "#FFFFCC80"
        "alta" -> "#FFA5D6A7"
        "media" -> "#FF80DEEA"
        else -> "#FFE4E4E4"
    }
}