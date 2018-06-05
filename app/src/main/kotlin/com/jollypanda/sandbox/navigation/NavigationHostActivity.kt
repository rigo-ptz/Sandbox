package com.jollypanda.sandbox.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jollypanda.sandbox.R
import kotlinx.android.synthetic.main.activity_navigation_host.*

/**
 * @author Yamushev Igor
 * @since  05.06.18
 */
class NavigationHostActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_host)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    override fun onSupportNavigateUp(): Boolean = NavHostFragment.findNavController(navActivityHostContainer).navigateUp()
    
}