package com.titin.pendientes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.domain.use_case.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {

    private val _uiState = MutableLiveData<List<ToDoUIModel>>()
    val uiState: LiveData<List<ToDoUIModel>> = _uiState


    fun getAllToDo() {
        viewModelScope.launch() {
            allUseCases.getAllToDoUseCase().collectLatest {
                _uiState.value = it
            }
        }
    }

    fun deleteToDo(toDoUIModel: ToDoUIModel) {
        viewModelScope.launch {
            allUseCases.deleteToDoUseCase.invoke(toDoUIModel = toDoUIModel)
        }
    }

    fun updateIsDone(toDoUIModel: ToDoUIModel) {
        viewModelScope.launch {
            allUseCases.insertOrUpdateToDoUseCase(
                name = toDoUIModel.name,
                isInsert = false,
                id = toDoUIModel.id,
                priority = toDoUIModel.priority,
                date = toDoUIModel.timestamp,
                isDone = toDoUIModel.isDone
            )
        }
    }
}