package com.titin.pendientes.ui.home.adapter

import android.content.ContentProvider
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.titin.pendientes.R
import com.titin.pendientes.utils.Constants
import com.titin.pendientes.utils.toFormat
import com.titin.pendientes.databinding.ToDoListRecyclerBinding
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.ui.home.HomeFragmentDirections
import kotlin.coroutines.coroutineContext

class ToDoListAdapter(val toDoList: ArrayList<ToDoUIModel>) :
    RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {

    class ToDoListViewHolder(val binding: ToDoListRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val binding = ToDoListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ToDoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.binding.recyclerNameTextView.text = toDoList[position].name
        holder.binding.recyclerPriorityTextView.text = toDoList[position].priority
        holder.binding.recyclerDateTextView.text =
            toDoList[position].timestamp.toFormat(Constants.CURRENT_DATE_FORMAT)
        holder.binding.checkbox.isChecked = toDoList[position].isDone
        holder.binding.cardView.setCardBackgroundColor(Color.parseColor(toDoList[position].priorityColor))

        if (toDoList[position].isDone) {
            holder.binding.recyclerNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.recyclerPriorityTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.recyclerDateTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.recyclerNameTextView.setTextColor(Color.DKGRAY)
            holder.binding.recyclerPriorityTextView.setTextColor(Color.DKGRAY)
            holder.binding.recyclerDateTextView.setTextColor(Color.DKGRAY)


            holder.binding.cardView.setCardBackgroundColor(Color.LTGRAY)

        }else {
            holder.binding.recyclerNameTextView.setTextColor(Color.BLACK)
            holder.binding.recyclerPriorityTextView.setTextColor(Color.BLACK)
            holder.binding.recyclerDateTextView.setTextColor(Color.BLACK)
            holder.binding.recyclerNameTextView.paintFlags = Paint.START_HYPHEN_EDIT_NO_EDIT
            holder.binding.recyclerPriorityTextView.paintFlags = Paint.END_HYPHEN_EDIT_NO_EDIT
            holder.binding.recyclerDateTextView.paintFlags = Paint.ANTI_ALIAS_FLAG


            holder.binding.cardView.setCardBackgroundColor(Color.parseColor(toDoList[position].priorityColor))
        }

        holder.binding.checkbox.setOnClickListener {

            onItemClickCheckBox?.invoke(
                ToDoUIModel(
                    id = toDoList[position].id,
                    name = toDoList[position].name,
                    isDone = !toDoList[position].isDone,
                    priorityColor = toDoList[position].priorityColor,
                    priority = toDoList[position].priority,
                    timestamp = toDoList[position].timestamp
                )
            )
        }

        holder.binding.recyclerUpdateButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToAddFragment(
                isInsert = false,
                id = toDoList[position].id
            )
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.recyclerDeleteButton.setOnClickListener {
            onItemClick?.invoke(toDoList[position])
        }

    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun updateList(newToDoList: List<ToDoUIModel>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }

    var onItemClick: ((ToDoUIModel) -> Unit)? = null
    var onItemClickCheckBox: ((ToDoUIModel) -> Unit)? = null


}