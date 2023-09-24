package com.example.androidassessmenttask.activities

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidassessmenttask.R
import com.example.androidassessmenttask.base.AndroidAssessmentBaseActivity
import com.example.androidassessmenttask.databinding.ActivityDashboardBinding

class DashboardActivity : AndroidAssessmentBaseActivity<ActivityDashboardBinding>() {

    override fun getLayout() = R.layout.activity_dashboard

    override fun setupViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.dashboard_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun setListeners() {
    }

    override fun setObservers() {
    }

    fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }
}
