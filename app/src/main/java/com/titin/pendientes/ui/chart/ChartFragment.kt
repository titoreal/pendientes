package com.titin.pendientes.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.titin.pendientes.R
import com.titin.pendientes.databinding.FragmentChartBinding
import com.titin.pendientes.domain.uimodel.ToDoUIModel
import com.titin.pendientes.utils.BaseFragment
import com.titin.pendientes.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.github.mikephil.charting.charts.PieChart

@AndroidEntryPoint
class ChartFragment : BaseFragment(R.layout.fragment_chart) {
    private var pieChart: PieChart? = null
    private val binding by viewBinding(FragmentChartBinding::bind)

    private val viewModel: ChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChart = binding.pieChart
        viewModel.getAllToDo()
        observeEvent()
        initViews()
    }

    private fun observeEvent() {

        viewModel.entity.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.messageTextView.visibility = View.VISIBLE
                binding.pieChart.visibility = View.GONE
            } else {
                setChartData(it)
                binding.pieChart.visibility = View.VISIBLE
                binding.messageTextView.visibility = View.GONE
            }
        })
    }

    private fun initViews() {
        binding.pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                e?.let {

                    val action = ChartFragmentDirections
                        .actionNavigationNotificationsToDetailChartFragment(it.data.toString())
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }

            override fun onNothingSelected() {}
        })

        bottomNavigationViewVisibility = View.VISIBLE
        toolbarVisibility = true
    }

    private fun setChartData(list: List<ToDoUIModel>) {
        // Agregar este log al principio de la función
        Log.d("ChartFragment", "setChartData called with ${list.size} items")

        var baja = 0f
        var media = 0f
        var alta = 0f
        var urgente = 0f

        list.forEach {
            when (it.priority.lowercase()) {
                "baja" -> baja++
                "alta" -> alta++
                "media" -> media++
                "urgente" -> urgente++
            }
        }

        // Agregar este log después de contar las prioridades
        Log.d(
            "ChartFragment",
            "Conteo de prioridades: Baja: $baja, Alta: $alta, Media: $media, Urgente: $urgente"
        )

        val pieEntry = mutableListOf<PieEntry>()
        pieEntry.add(PieEntry(baja, "Baja"))
        pieEntry.add(PieEntry(alta, "Alta"))
        pieEntry.add(PieEntry(media, "Media"))
        pieEntry.add(PieEntry(urgente, "Urgente"))


        val pieDataSet = PieDataSet(pieEntry, "")
        pieDataSet.setColors(
            ContextCompat.getColor(requireContext(), R.color.priority_low),
            ContextCompat.getColor(requireContext(), R.color.priority_high),
            ContextCompat.getColor(requireContext(), R.color.priority_medium),
            ContextCompat.getColor(requireContext(), R.color.priority_urgent)
        )
        pieDataSet.valueTextSize = 30f
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.formSize = 30f
        pieDataSet.form = Legend.LegendForm.CIRCLE
        val data = PieData(pieDataSet)

        pieChart?.let { chart ->
            chart.data = data
            chart.animateY(2000)
            chart.invalidate()
            // Agregar este log después de configurar el chart
            Log.d("ChartFragment", "PieChart configurado y datos asignados")
        } ?: Log.e("ChartFragment", "pieChart es nulo")
    }
}