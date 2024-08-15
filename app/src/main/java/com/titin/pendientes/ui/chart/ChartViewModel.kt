package com.titin.pendientes.ui.chart

import androidx.lifecycle.*
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.domain.use_case.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val allToDoUseCase: AllUseCases
) : ViewModel() {

    private val _entities = MutableLiveData<List<ToDoUIModel>>()
    val entity: LiveData<List<ToDoUIModel>> = _entities

    fun getAllToDo() {
        viewModelScope.launch() {
            allToDoUseCase.getAllToDoUseCase().collectLatest() { entities ->
                _entities.value = entities
            }
        }
    }
}