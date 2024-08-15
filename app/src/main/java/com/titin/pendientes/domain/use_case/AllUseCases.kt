package com.titin.pendientes.domain.use_case

data class AllUseCases(
    val deleteToDoUseCase: DeleteToDoUseCase,
    val getAllToDoUseCase: GetAllToDoUseCase,
    val getSingleToDoUseCase: GetSingleToDoUseCase,
    val getToDoByPriorityUseCase: GetToDoByPriorityUseCase,
    val insertOrUpdateToDoUseCase: InsertOrUpdateToDoUseCase
)