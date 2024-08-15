package com.titin.pendientes.ui.detail_pie_chart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.titin.pendientes.R
import com.titin.pendientes.databinding.FragmentDetailChartBinding
import com.titin.pendientes.ui.detail_pie_chart.adapter.DetailChartAdapter
import com.titin.pendientes.utils.BaseFragment
import com.titin.pendientes.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChartFragment : BaseFragment(R.layout.fragment_detail_chart) {

    private val binding by viewBinding(FragmentDetailChartBinding::bind)

    private val viewModel: DetailChartViewModel by viewModels()

    private val detailChartAdapter = DetailChartAdapter(arrayListOf())
    private var priority: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            priority = DetailChartFragmentArgs.fromBundle(it).type
        }
        viewModel.getToDoByPriority(priority!!)
        observeEvents()
        initViews()
    }

    private fun observeEvents() {
        viewModel.uiState.observe(viewLifecycleOwner) { todo->
            todo?.let {
                detailChartAdapter.updateList(it)
            }
        }
    }

    private fun initViews() {
        binding.recyclerView.adapter = detailChartAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bottomNavigationViewVisibility = View.GONE
        toolbarVisibility = true
    }
}