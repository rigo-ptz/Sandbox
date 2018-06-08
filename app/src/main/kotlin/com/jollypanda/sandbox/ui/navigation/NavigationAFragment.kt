/*
package com.jollypanda.sandbox.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.jollypanda.sandbox.R
import kotlinx.android.synthetic.main.fragment_navigation_a.*


class NavigationAFragment : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_navigation_a, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvToStart.setOnClickListener {
            NavHostFragment.findNavController(this)
            NavHostFragment.findNavController(this).navigateUp()
        }
        tvToB.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.actionFromAToB)
        }
    }
    
    companion object {
        @JvmStatic
        fun newInstance() = NavigationAFragment()
    }
}
*/
