package com.example.minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minutesworkout.databinding.ItemHistoryRowBinding

class ItemAdapter(val items: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(binding:ItemHistoryRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val llm = binding.llHistoryItemMain
        val tvPosition = binding.tvPosition;
        val tvItem = binding.tvItem;

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llm.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llm.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }
}