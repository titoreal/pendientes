package com.titin.pendientes.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.titin.pendientes.R
import com.titin.pendientes.databinding.FragmentAboutBinding
import com.titin.pendientes.utils.viewBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private val binding by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}