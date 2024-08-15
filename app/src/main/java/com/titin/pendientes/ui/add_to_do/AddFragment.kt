package com.titin.pendientes.ui.add_to_do

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.titin.pendientes.R
import com.titin.pendientes.utils.Constants
import com.titin.pendientes.utils.toFormat
import com.titin.pendientes.databinding.FragmentAddBinding
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.utils.BaseFragment
import com.titin.pendientes.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


import java.util.*

@AndroidEntryPoint
class AddFragment : BaseFragment(R.layout.fragment_add) {

    private val binding by viewBinding(FragmentAddBinding::bind)

    private val viewModel: AddViewModel by viewModels()

    private var isInsert = true
    private var id: Int? = null
    private var priority: String? = null
    private var selectedDate = Date()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvent()
        arguments?.let {
            isInsert = AddFragmentArgs.fromBundle(it).isInsert
            id = AddFragmentArgs.fromBundle(it).id
        }
        id?.let { viewModel.getById(it) }
    }

    private fun observeEvent() {
        viewModel.singleToDo.observe(viewLifecycleOwner) { todo ->
            todo?.let {
                setUIState(it)
            }
        }
    }


    private fun setUIState(entity: ToDoUIModel) {
        binding.dateButton.text = entity.timestamp.toFormat(Constants.CURRENT_DATE_FORMAT)
        binding.nameEditText.setText(entity.name)
        binding.saveButton.text = "Update"
        binding.saveButton.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.new_button
            )
        )
        binding.saveButton.icon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_edit_24)

        when (entity.priority) {
            "baja" -> binding.radioButtonBaja.isChecked = true
            "alta" -> binding.radioButtonAlta.isChecked = true
            "media" -> binding.radioButtonMedia.isChecked = true
            "urgente" -> binding.radioButtonUrgente.isChecked = true
        }
    }

    private fun initViews() {
        binding.saveButton.setOnClickListener {
            viewModel.insertOrUpdate(
                name = binding.nameEditText.text.toString(),
                isInsert = isInsert,
                id = id,
                priority = priority,
                date = selectedDate
            )
            findNavController().popBackStack()
        }

        binding.dateButton.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date_button))
                    .setSelection(selectedDate.time)
                    .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                //set and define the calendar instance to local timezone. It prevents Calendar sets one day before the selected.
                val selectedUtc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                selectedUtc.timeInMillis = timestamp
                val selectedLocal = Calendar.getInstance()
                selectedLocal.clear()
                selectedLocal.set(
                    selectedUtc.get(Calendar.YEAR),
                    selectedUtc.get(Calendar.MONTH),
                    selectedUtc.get(Calendar.DATE)
                )

                selectedDate = selectedLocal.time
                binding.dateButton.text = selectedLocal.time.toFormat(Constants.CURRENT_DATE_FORMAT)
            }
            datePicker.show(parentFragmentManager, Constants.TAG_DATE_PICKER);
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            priority = when (checkedId) {
                R.id.radioButtonAlta -> "alta"
                R.id.radioButtonBaja -> "baja"
                R.id.radioButtonMedia -> "media"
                R.id.radioButtonUrgente -> "urgente"
                else -> ""
            }
        }

        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = true
    }
}