package com.titin.pendientes.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.titin.pendientes.ui.activity.MainActivity

abstract class BaseFragment(fragmentId: Int): Fragment(fragmentId) {

    protected open var toolbarVisibility = true
    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            val mainActivity =  activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
            mainActivity.setToolbarVisibility(toolbarVisibility)
        }
    }
}