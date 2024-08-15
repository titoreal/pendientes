package com.titin.pendientes.ui.detail_pie_chart

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
class DetailChartViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {

    private val _uiState = MutableLiveData<List<ToDoUIModel>>()
    val uiState: LiveData<List<ToDoUIModel>> = _uiState

    fun getToDoByPriority(priority: String) {
        viewModelScope.launch {
            allUseCases.getToDoByPriorityUseCase(priority = priority).collectLatest {
                _uiState.value = it
            }
        }
    }
}