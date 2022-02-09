package com.example.llegadasemt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.llegadasemt.R
import com.example.llegadasemt.data.Hello

class ArrivalsAdapter: RecyclerView.Adapter<ArrivalsAdapter.ArrivalsViewHolder>() {
    var arrivals = listOf<Hello>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalsViewHolder {
        return ArrivalsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bus_arrival, parent, false))
    }

    override fun onBindViewHolder(holder: ArrivalsViewHolder, position: Int) {
        holder.bind(arrivals[position])
    }

    override fun getItemCount(): Int {
        return arrivals.size
    }

    class ArrivalsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val line: TextView = view.findViewById(R.id.line)
        val time: TextView = view.findViewById(R.id.time)

        fun bind(arrival: Hello) {
            line.text = arrival.status
            time.text = arrival.date
        }
    }
}