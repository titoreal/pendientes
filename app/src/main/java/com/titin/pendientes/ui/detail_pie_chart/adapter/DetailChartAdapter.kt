package com.titin.pendientes.ui.detail_pie_chart.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.titin.pendientes.utils.Constants
import com.titin.pendientes.utils.toFormat
import com.titin.pendientes.databinding.DetailChartRecyclerBinding
import com.titin.pendientes.domain.uimodel.ToDoUIModel

class DetailChartAdapter(val toDoList: ArrayList<ToDoUIModel>) : RecyclerView.Adapter<DetailChartAdapter.DetailChartViewHolder>(){

    class DetailChartViewHolder(val binding: DetailChartRecyclerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChartViewHolder {
        val binding = DetailChartRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailChartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailChartViewHolder, position: Int) {
        holder.binding.recyclerNameTextView.text = toDoList[position].name
        holder.binding.recyclerPriorityTextView.text = toDoList[position].priority
        holder.binding.recyclerDateTextView.text = toDoList[position].timestamp.toFormat(
            Constants.CURRENT_DATE_FORMAT
        )

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
    }



    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun updateList(newToDoList: List<ToDoUIModel>) {
        toDoList.clear()
        toDoList.addAll(newToDoList)
        notifyDataSetChanged()
    }

}