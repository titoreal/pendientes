package com.titin.pendientes.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ToDoUIModel(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val priority: String,
    val timestamp: Date,
    val priorityColor: String
) : Parcelable

