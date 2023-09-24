package com.example.androidassessmenttask.ui.home.viewmodels

import com.example.androidassessmenttask.base.AndroidAssessmentBaseViewModel
import com.example.androidassessmenttask.constants.Constants

class HomeViewModel : AndroidAssessmentBaseViewModel() {
    fun isValidLength(name: String?): Boolean {
        return !name.isNullOrEmpty() && name.length > 2
    }

    fun validateName(name: String?): String? {
        return when {
            name.isNullOrEmpty() -> "We need your First Name"
            name.matches(Constants.USERNAME_REGEX) -> null
            else -> "What kind of first name is that?"
        }
    }
}