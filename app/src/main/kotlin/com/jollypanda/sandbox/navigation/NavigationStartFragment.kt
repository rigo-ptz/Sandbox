package com.jollypanda.sandbox.navigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.jollypanda.sandbox.R
import kotlinx.android.synthetic.main.fragment_navigation_start.*

/**
 * @author Yamushev Igor
 * @since  05.06.18
 */
class NavigationStartFragment : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_navigation_start, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvToA.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.actionFromStartToA)
        }
    }
    
    companion object {
        @JvmStatic
        fun newInstance(context: Context) = NavigationStartFragment()
    }
}