package com.titin.pendientes.domain.use_case

import com.titin.pendientes.domain.mapper.ToDoEntityMapper
import com.titin.pendientes.domain.repository.ToDoRepository
import javax.inject.Inject

class GetSingleToDoUseCase @Inject constructor(
    private val repository: ToDoRepository,
    private val mapper: ToDoEntityMapper
) {

    suspend operator fun invoke(id: Int) = repository.getToDoById(id = id).run {
        mapper.map(entity = this)
    }



}
