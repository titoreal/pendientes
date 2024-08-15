package com.titin.pendientes.ui.add_to_do

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titin.pendientes.data.local.ToDoEntity
import com.titin.pendientes.domain.repository.ToDoRepository
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.domain.use_case.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: ToDoRepository,
    private val allUseCases: AllUseCases
) : ViewModel(){

    private val _singleToDo = MutableLiveData<ToDoUIModel>()
    val singleToDo: LiveData<ToDoUIModel> = _singleToDo

    fun getById(id: Int?) {
        viewModelScope.launch {
            if (id != 0 && id != null) {
                _singleToDo.value = allUseCases.getSingleToDoUseCase.invoke(id = id)
            }

        }
    }
    fun insertOrUpdate(name: String, isInsert: Boolean, id: Int?, priority: String?, date: Date) {
        viewModelScope.launch {
            if (isInsert) {
                repository.insertToDo(
                    ToDoEntity(
                        name = name,
                        priority = priority,
                        timestamp = date
                    )
                )
            } else {
                repository.updateToDo(
                    ToDoEntity(
                        id = id ?: 0,
                        name = name,
                        priority = priority,
                        timestamp = date
                    )
                )
            }
        }
    }
}