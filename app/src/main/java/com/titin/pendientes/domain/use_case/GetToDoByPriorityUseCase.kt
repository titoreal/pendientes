package com.titin.pendientes.domain.use_case

import com.titin.pendientes.domain.mapper.ToDoEntityMapper
import com.titin.pendientes.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetToDoByPriorityUseCase @Inject constructor(
    private val repository: ToDoRepository,
    private val mapper: ToDoEntityMapper,

) {

    operator fun invoke(priority: String) = repository.getToDoByPriority(priority = priority).map { toDoList ->
        toDoList.map {
            mapper.map(entity = it)
        }
    }
}
