package com.example.androidassessmenttask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class AndroidAssessmentBaseFragment<DB : ViewDataBinding>(private val showActionBar: Boolean = false) :
    Fragment() {
    lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayout(), null, false)
        (activity as AppCompatActivity).supportActionBar?.apply { if (showActionBar) show() else hide() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setupViews()
        setListeners()
    }

    abstract fun getLayout(): Int
    abstract fun setupViews()
    abstract fun setListeners()
    abstract fun setObservers()
}