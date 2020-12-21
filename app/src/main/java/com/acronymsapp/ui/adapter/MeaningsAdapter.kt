package com.acronymsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acronymsapp.R
import com.acronymsapp.repository.remote.model.LongForm

class MeaningsAdapter : RecyclerView.Adapter<MeaningsAdapter.MeaningsHolder>() {

    private var longFormList: List<LongForm> = emptyList()

    fun setData(longFormList: List<LongForm>) {
        this.longFormList = longFormList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MeaningsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount() = if (longFormList.isNullOrEmpty()) 0 else longFormList.size

    override fun onBindViewHolder(holder: MeaningsHolder, position: Int) {
        val longForms = longFormList[position]

        // bind data
        holder.longForm.text = longForms.longform
        holder.frequency.text = "Frequency: ${longForms.frequency}"
        holder.since.text = "Since: ${longForms.since}"
    }

    class MeaningsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val longForm = view.findViewById<TextView>(R.id.longForm)
        val frequency = view.findViewById<TextView>(R.id.frequency)
        val since = view.findViewById<TextView>(R.id.since)
    }
}