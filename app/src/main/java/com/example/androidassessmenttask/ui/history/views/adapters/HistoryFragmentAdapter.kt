package com.example.androidassessmenttask.ui.history.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassessmenttask.R
import com.example.androidassessmenttask.core.models.UserDataModel
import com.example.androidassessmenttask.ui.history.views.viewholders.HistoryItemViewHolder

class HistoryFragmentAdapter(
    private var list: List<UserDataModel>
) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryItemViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history,
            null,
            false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}