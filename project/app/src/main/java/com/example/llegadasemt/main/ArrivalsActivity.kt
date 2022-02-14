package com.example.llegadasemt.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.llegadasemt.adapter.ArrivalsAdapter
import com.example.llegadasemt.databinding.ActivityArrivalsBinding
import com.example.llegadasemt.network.RequestView

class ArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrivalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.arrivalsToolbar)

        var stop: String = intent.extras?.get("stop") as String
        binding.arrivalsToolbar.setTitle(binding.arrivalsToolbar.title.toString() + " " + stop)
        val requests: RequestView = RequestView(intent.extras?.get("token") as String)

        val arrivalsList: RecyclerView = binding.arrivalsList
        val arrivalsAdapter: ArrivalsAdapter = ArrivalsAdapter()
        arrivalsList.adapter = arrivalsAdapter
        requests.getArrivals(stop, arrivalsAdapter, binding.emptyArrive)
        //binding.emptyArrive.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0,0)
    }

    override fun onSupportNavigateUp(): Boolean {
        val result = super.onSupportNavigateUp()
        overridePendingTransition(0,0)
        return result
    }
}