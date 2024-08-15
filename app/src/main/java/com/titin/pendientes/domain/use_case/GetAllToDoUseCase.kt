package com.titin.pendientes.domain.use_case

import com.titin.pendientes.domain.mapper.ToDoEntityMapper
import com.titin.pendientes.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllToDoUseCase @Inject constructor(
    private val repository: ToDoRepository,
    private val mapper: ToDoEntityMapper
) {

    operator fun invoke() = repository.getAllToDoList().map { toDoList->
        toDoList.map {
            mapper.map(entity = it)
        }

    }
}