package com.example.androidassessmenttask.ui.history.views.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.androidassessmenttask.core.models.UserDataModel
import com.example.androidassessmenttask.databinding.ItemHistoryBinding

class HistoryItemViewHolder(private val binding: ItemHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(userDataModel: UserDataModel) {
        binding.nameText.text = userDataModel.name
        binding.ageText.text = userDataModel.age.toString()
    }
}