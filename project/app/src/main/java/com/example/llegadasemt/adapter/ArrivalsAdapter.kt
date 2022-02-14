package com.example.llegadasemt.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.llegadasemt.R
import com.example.llegadasemt.data.MyArrival

class ArrivalsAdapter: RecyclerView.Adapter<ArrivalsAdapter.ArrivalsViewHolder>() {
    var arrivals = listOf<MyArrival>()
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
        val nextTime: TextView = view.findViewById(R.id.next_time)
        val destination: TextView = view.findViewById(R.id.destination)
        var drawable: Drawable = line.background

        fun bind(arrival: MyArrival) {
            line.text = arrival.line
            time.text = arrival.estimatedTime
            nextTime.text = arrival.nextEstimatedTime
            destination.text = arrival.destination
            drawable = drawable.mutate()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setTint(Color.parseColor(arrival.bgColor))
                line.background = drawable
            }
        }
    }
}