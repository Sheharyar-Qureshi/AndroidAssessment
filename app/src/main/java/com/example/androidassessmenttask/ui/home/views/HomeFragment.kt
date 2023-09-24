package com.example.androidassessmenttask.ui.home.views

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.androidassessmenttask.R
import com.example.androidassessmenttask.activities.DashboardActivity
import com.example.androidassessmenttask.base.AndroidAssessmentBaseFragment
import com.example.androidassessmenttask.databinding.FragmentHomeBinding
import com.example.androidassessmenttask.ui.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : AndroidAssessmentBaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_home

    override fun setupViews() {
    }

    override fun setListeners() {
        binding.userNameEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.validationText.visibility = View.GONE
                binding.guessAgeButton.isEnabled = viewModel.isValidLength(s.toString().trim(' '))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.guessAgeButton.setOnClickListener {
            binding.userNameEditText.text.toString().let { name ->
                handleValidations(name.trim(' '))
            }
        }
    }

    override fun setObservers() {
    }

    private fun navigateToResultFragment(userName: String) {
        val action =
            HomeFragmentDirections.actionNavigationHomeToNavigationResult(userName = userName)
        findNavController().navigate(action)
        (activity as DashboardActivity).hideBottomNav()
    }

    override fun onResume() {
        super.onResume()
        (activity as DashboardActivity).showBottomNav()
    }

    private fun handleValidations(name: String) {
        viewModel.validateName(name)?.let { error ->
            binding.validationText.text = error
            binding.validationText.visibility = View.VISIBLE
        } ?: run {
            navigateToResultFragment(name)
        }
    }

}